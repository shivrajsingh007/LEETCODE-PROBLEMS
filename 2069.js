class Solution {
  constructor(width, height) {
    this.pos = [];
    this.isOrigin = true;
    this.i = 0;
    this.pos.push([[0, 0], "South"]);
    for (let i = 1; i < width; ++i)
      this.pos.push([[i, 0], "East"]);
    for (let j = 1; j < height; ++j)
      this.pos.push([[width - 1, j], "North"]);
    for (let i = width - 2; i >= 0; --i)
      this.pos.push([[i, height - 1], "West"]);
    for (let j = height - 2; j > 0; --j)
      this.pos.push([[0, j], "South"]);
  }

  step(num) {
    this.isOrigin = false;
    this.i = (this.i + num) % this.pos.length;
  }

  getPos() {
    return this.pos[this.i][0];
  }

  getDir() {
    return this.isOrigin ? "East" : this.pos[this.i][1];
  }
}