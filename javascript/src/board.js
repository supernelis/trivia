const Category = require("./category");

module.exports = function() {
    const nbCells = 12;
    const nbCategories = Object.keys(Category).length;
    const playersPosition = new Map();
    const penaltyBox = new Set();

    this.move = (player, steps) => {
		const newPosition = (this.positionOf(player) + steps) % nbCells;
		playersPosition.set(player, newPosition);
	}

    this.positionOf = (player) => playersPosition.get(player) || 0;
    
    this.addToThePenaltyBox = (player) => penaltyBox.add(player);

	this.removeFromPenaltyBox = (player) => penaltyBox.delete(player);

    this.isInPenaltyBox = (player) => penaltyBox.has(player);
    
    this.categoryFor = (position) => {
		const scaledPosition = position % nbCategories
		return Category[Object.keys(Category)[scaledPosition]];
	};
}