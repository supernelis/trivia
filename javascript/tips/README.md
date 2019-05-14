# Tips

## How to fake [Math.random](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Math/random)

```javascript
var seed = 1;
Math.random = function () {
  var x = Math.sin(seed++) * 10000;
  return x - Math.floor(x);
}
```

## How to capture the console output

```javascript
console.oldLog = console.log;
var result = "";
console.log = function (value) {
  result += value + "\n";
};
console.oldLog(result);
```

## How to save outputs with [Approval](http://approvaltests.com/)

```javascript
this.verify("your result here", { reporters: [] });
```