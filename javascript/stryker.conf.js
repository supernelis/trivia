module.exports = function(config) {
  config.set({
    mutator: "javascript",
    packageManager: "npm",
    reporters: ["clear-text", "progress", "html"],
    testRunner: "mocha",
    transpilers: [],
    coverageAnalysis: "off"
  });
};
