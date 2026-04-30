"use strict";

function computePolkadotScore(asciiArt) {
  const lines = asciiArt.split(/\r?\n/);

  const eyeLineIndex = lines.findIndex(
    (line, index) => index < Math.ceil(lines.length / 2) && [...line].filter((ch) => ch === "•").length >= 2
  );

  if (eyeLineIndex === -1) {
    throw new Error("Could not find Angelica's pupils.");
  }

  const pupilCharCount = [...lines[eyeLineIndex]].filter((ch) => ch === "•").length;
  const lipLine = lines[eyeLineIndex + 1];

  if (!lipLine) {
    throw new Error("Could not find Angelica's lips.");
  }

  // The next line contains the smile corners as semicolons; the special
  // range spans from the first lip character to the last lip character.
  const lipColumns = [];
  [...lipLine].forEach((ch, column) => {
    if (ch === ";") {
      lipColumns.push(column);
    }
  });

  if (lipColumns.length < 2) {
    throw new Error("Could not determine the lips range.");
  }

  const lipStart = Math.min(...lipColumns);
  const lipEnd = Math.max(...lipColumns);

  let inside = 0;
  let outside = 0;

  for (const line of lines) {
    [...line].forEach((ch, column) => {
      if (ch !== "O") {
        return;
      }

      if (column >= lipStart && column <= lipEnd) {
        inside += 1;
      } else {
        outside += 1;
      }
    });
  }

  return outside + inside * pupilCharCount;
}

module.exports = { computePolkadotScore };

if (require.main === module) {
  const fs = require("node:fs");
  const path = process.argv[2] || "angelica.txt";
  const art = fs.readFileSync(path, "utf8");

  console.log(computePolkadotScore(art));
}
