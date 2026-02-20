from flask import Flask, request, jsonify
from services.gemini_service import generate_mcqs
from utils.prompt_builder import build_prompt
from utils.response_parser import parse_questions

app = Flask(__name__)


@app.route("/health", methods=["GET"])
def health():
    return jsonify({"status": "ok"}), 200


@app.route("/generate-questions", methods=["POST"])
def generate_questions():
    try:
        data = request.get_json()

        if not data:
            return jsonify({"error": "Invalid or missing JSON body"}), 400

        text = data.get("text")
        category = data.get("category")
        num_questions = data.get("numQuestions", 5)

        if not text or not category:
            return jsonify({"error": "text and category are required"}), 400

        prompt = build_prompt(text, category, num_questions)

        raw_response = generate_mcqs(prompt)

        print("RAW RESPONSES: ", raw_response)

        if not raw_response:
            return jsonify({
                "error": "Empty response from AI service"
            }), 502

        try:
            questions = parse_questions(raw_response, category)
        except Exception as parse_error:
            return jsonify({
                "error": "Failed to parse AI response",
                "details": str(parse_error),
                "raw_response": raw_response
            }), 500

        return jsonify([q.to_dict() for q in questions]), 200

    except Exception as e:
        return jsonify({
            "error": "Internal server error",
            "details": str(e)
        }), 500


if __name__ == "__main__":
    app.run(debug=True)