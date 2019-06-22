module.exports = function(name) {
    this.name = name;
    this.coins = 0;

    this.addCoin = function(){
        this.coins += 1;
    }

    this.hasWon = function() {
        return this.coins == 6;
    }
}