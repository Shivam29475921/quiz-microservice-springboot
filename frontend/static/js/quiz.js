let current = 0;
let answers = {};

function loadQuestion() {

let q = quiz.questions[current];

document.getElementById("question-title").innerText =
`Q${current + 1}. ${q.questionTitle}`;

document.getElementById("options-container").innerHTML =
q.options.map(opt => `
<div class="option ${answers[q.id] === opt ? "selected" : ""}"
onclick="selectOption('${q.id}','${opt}')">${opt}</div>
`).join("");

updateProgress();
updatePalette();
}

function selectOption(qid, option) {
answers[qid] = option;
loadQuestion();
}

function nextQuestion(){ if(current < quiz.questions.length-1){ current++; loadQuestion();}}
function prevQuestion(){ if(current>0){ current--; loadQuestion();}}
function jumpTo(i){ current=i; loadQuestion();}

function updateProgress(){
let percent = (Object.keys(answers).length/quiz.questions.length)*100;
document.getElementById("progress").style.width = percent+"%";
}

function updatePalette(){
document.querySelectorAll(".palette-item").forEach((box,i)=>{
let qid = quiz.questions[i].id;
box.classList.toggle("answered",answers[qid]);
box.classList.toggle("current",i===current);
});
}

function submitQuiz() {

    const payload = quiz.questions.map(q => ({
        questionId: q.id,
        userAnswer: answers[q.id] || null
    }));

    fetch(`/quiz/${quiz.id}/submit`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    })
    .then(res => res.text())
    .then(html => {
        document.open();
        document.write(html);
        document.close();
    });
}

loadQuestion();