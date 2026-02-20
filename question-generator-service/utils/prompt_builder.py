def build_prompt(text, category, num_questions):
    return f"""
You are an MCQ generator.

Generate {num_questions} multiple choice questions from the given text.

Rules:
- Each question must have 4 options
- Only one correct answer
- Return STRICT JSON
- Do not add explanations

JSON format:
[
  {{
    "questionTitle": "...",
    "options": ["...", "...", "...", "..."],
    "correctOption": "..."
  }}
]

Category: {category}

Text:
{text}
"""