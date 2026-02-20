from google import genai
from config import Config

client = genai.Client(
    api_key=Config.GEMINI_API_KEY,
    http_options={"api_version": "v1"}
)

def generate_mcqs(prompt: str) -> str:
    response = client.models.generate_content(
        model="models/gemini-2.5-flash",
        contents=prompt,
        # config={
        #     "response_mime_type": "application/json"
        # }

    )

    return response.text
