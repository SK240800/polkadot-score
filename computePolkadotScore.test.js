"use strict";

const test = require("node:test");
const assert = require("node:assert/strict");
const fs = require("node:fs");
const path = require("node:path");
const { computePolkadotScore } = require("./computePolkadotScore");

test("computes the expected score for Angelica", () => {
  const art = fs.readFileSync(path.join(__dirname, "angelica.txt"), "utf8");

  assert.equal(computePolkadotScore(art), 64);
});
