from flask import Flask, render_template, request, redirect, url_for
import requests

app = Flask(__name__)

BASE = "http://localhost:8080"


# ---------------- DASHBOARD ----------------
@app.route("/")
def home():
    return render_template("dashboard.html")


# ---------------- QUIZ SECTION ----------------
@app.route("/quiz-section")
def quiz_section():
    return render_template("quiz_section.html")


@app.route("/quiz-list")
def quiz_list():

    search_text = request.args.get("query")
    search_type = request.args.get("type")

    quizzes = []

    if search_text and search_type:

        if search_type == "title":
            url = f"{BASE}/quiz/quizTitle/{search_text}"

        elif search_type == "category":
            url = f"{BASE}/quiz/category/{search_text}"

        else:
            url = None

        if url:
            res = requests.get(url)

            if res.ok:
                quizzes = res.json()

                # backend may return single object
                if isinstance(quizzes, dict):
                    quizzes = [quizzes]

    else:
        res = requests.get(f"{BASE}/quiz")
        quizzes = res.json() if res.ok else []

    return render_template(
        "quiz_list.html",
        quizzes=quizzes,
        query=search_text,
        search_type=search_type
    )
# ---------------- CREATE QUIZ ----------------
@app.route("/create-quiz", methods=["GET", "POST"])
def create_quiz():
    if request.method == "POST":
        payload = request.form.to_dict()
        requests.post(f"{BASE}/quiz/create", json=payload)
        return redirect(url_for("quiz_list"))

    return render_template("create_quiz.html")


# ---------------- ATTEMPT QUIZ ----------------
@app.route("/quiz/<quiz_id>")
def attempt_quiz(quiz_id):
    res = requests.get(f"{BASE}/quiz/{quiz_id}")
    quiz = res.json()
    return render_template("quiz_attempt.html", quiz=quiz)


@app.route("/quiz/<quiz_id>/submit", methods=["POST"])
def submit_quiz(quiz_id):
    answers = request.json
    res = requests.post(f"{BASE}/quiz/{quiz_id}/submit", json=answers)
    result = res.json()
    print(result)
    return render_template("result.html", result=result)


# ---------------- GEMINI GENERATOR ----------------
@app.route("/ai-generator", methods=["GET", "POST"])
def ai_generator():
    if request.method == "POST":
        payload = request.form.to_dict()
        res = requests.post(f"{BASE}/question-generator/generate", json=payload)
        return render_template("generated_questions.html", questions=res.json())

    return render_template("ai_generator.html")


if __name__ == "__main__":
    app.run(debug=True, port=5001)