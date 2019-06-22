const Category = require("./category");
const nbQuestionsPerCategory = 50;

module.exports = function() {
    const questions = generateQuestions();
    
    this.questionFor = (category) => questions.get(category).shift();
}

function generateQuestions() {
    const questions = new Map();

    Object.values(Category).forEach((category) => {
        const categoryQuestions = generateQuestionsForCategory(category);
        questions.set(category, categoryQuestions);
    });

    function generateQuestionsForCategory(category) {
        const categoryQuestions = [];

        for (var questionNumber = 0; questionNumber < nbQuestionsPerCategory; questionNumber++) {
            categoryQuestions.push(`${category} Question ${questionNumber}`);
        }

        return categoryQuestions;
    }

    return questions;
}