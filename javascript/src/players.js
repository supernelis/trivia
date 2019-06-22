module.exports = function () {
    const players = [];

    this.count = () => players.length;
    this.add = (player) => players.push(player);
    this.nextAfter = (currentPlayer) => {
        if (!currentPlayer) return players[0];

        let currentPlayerIndex = players.indexOf(currentPlayer);
        let nextPayerIndex = (currentPlayerIndex + 1) % players.length;
        return players[nextPayerIndex];
    }
}