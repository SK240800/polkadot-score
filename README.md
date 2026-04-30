# polkadot-score

Small Node.js solution for the Angelica ASCII-art `computePolkadotScore` challenge.

## Result

For the provided `angelica.txt`, the score is `64`.

## Usage

```bash
npm test
npm start
```

## How it works

- finds the eye line by locating the first upper-half line with two `•` pupil characters
- uses the two `;` characters on the following line as the inclusive lips range
- counts dress `O` polkadots inside and outside that x-range
- returns `outside + inside * pupilCharCount`
