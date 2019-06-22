module.exports = function() {
    let answer = Math.floor(Math.random() * 10);

    this.isCorrect = () => answer != 7;
}