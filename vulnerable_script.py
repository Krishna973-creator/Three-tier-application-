# vulnerable_script.py
import os
from flask import Flask, request, escape

app = Flask(__name__)

@app.route("/")
def index():
    name = request.args.get("name", "World")
    # WARNING: Rendering user-provided data without proper escaping!
    output = f"<h1>Hello, {name}!</h1>"
    return output

if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=int(os.environ.get("PORT", 5000)))
