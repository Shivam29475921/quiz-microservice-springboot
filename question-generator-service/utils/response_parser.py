import json
import re
from models.question_model import Question


def parse_questions(raw_text, category):

    if not raw_text:
        raise ValueError("Empty AI response")

    # 🔥 Remove markdown code fences like ```json ... ```
    cleaned = re.sub(r"```json|```", "", raw_text).strip()

    print("=" * 50)
    # 🔥 Extract ONLY the JSON array
    cleaned = re.sub(r"\n", "", cleaned).strip()
    print(cleaned)
    data = json.loads(cleaned)
    print("=")
    print(data)

    questions = []

    for item in data:
        q = Question(
            questionTitle=item["questionTitle"],
            questionCategory=category,
            options=item["options"],
            correctOption=item["correctOption"]
        )
        questions.append(q)

    print("=" * 50)
    print(questions)
    return questions