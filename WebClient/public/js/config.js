// map matrix
var map = [];
//         0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 
map[0] =  [0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0];
map[1] =  [0, 5, 5, 5, 5, 5, 5, 0, 0, 5, 5, 5, 5, 0, 0, 5, 5, 5, 5, 5, 5, 0];
map[2] =  [0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0];
map[3] =  [0, 4, 2, 2, 2, 2, 4, 2, 4, 5, 2, 2, 5, 4, 2, 2, 2, 2, 2, 4, 5, 0];
map[4] =  [0, 4, 4, 4, 4, 2, 4, 2, 4, 5, 0, 0, 5, 4, 2, 4, 4, 4, 2, 4, 5, 0];
map[5] =  [0, 4, 5, 5, 4, 2, 4, 2, 4, 5, 0, 0, 5, 4, 2, 4, 5, 4, 2, 4, 5, 0];
map[6] =  [0, 4, 5, 5, 4, 2, 4, 2, 4, 5, 0, 0, 5, 4, 2, 4, 5, 4, 2, 4, 5, 0];
map[7] =  [0, 4, 5, 5, 4, 2, 4, 2, 4, 5, 0, 0, 5, 4, 2, 4, 5, 4, 2, 4, 5, 0];
map[8] =  [0, 4, 5, 5, 4, 2, 4, 2, 4, 5, 0, 0, 5, 4, 2, 4, 5, 4, 2, 4, 5, 0];
map[9] =  [0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0];
map[10] = [0, 4, 2, 2, 2, 2, 2, 2, 4, 5, 5, 5, 5, 4, 2, 2, 2, 2, 2, 2, 2, 0];
map[11] = [0, 4, 2, 2, 2, 2, 2, 2, 4, 5, 5, 5, 5, 4, 2, 2, 2, 2, 2, 2, 2, 0];
map[12] = [0, 4, 5, 2, 5, 5, 2, 5, 4, 5, 5, 5, 5, 4, 4, 4, 4, 4, 4, 3, 3, 0];
map[13] = [0, 4, 5, 2, 5, 5, 2, 5, 4, 5, 5, 5, 5, 4, 5, 5, 5, 5, 5, 3, 3, 0];
map[14] = [0, 4, 5, 2, 5, 4, 4, 4, 4, 4, 4, 1, 4, 4, 2, 2, 2, 2, 2, 2, 2, 0];
map[15] = [0, 4, 5, 2, 5, 5, 2, 5, 4, 5, 0, 0, 5, 4, 2, 2, 2, 2, 2, 2, 2, 0];
map[16] = [0, 4, 5, 2, 5, 5, 2, 5, 4, 5, 0, 0, 5, 4, 4, 4, 4, 4, 4, 4, 5, 0];
map[17] = [0, 4, 2, 2, 2, 2, 2, 2, 4, 5, 0, 0, 5, 4, 2, 2, 2, 2, 2, 4, 5, 0];
map[18] = [0, 4, 2, 2, 2, 2, 2, 2, 4, 5, 0, 0, 5, 4, 4, 4, 4, 4, 2, 4, 5, 0];
map[19] = [0, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0, 0, 5, 4, 5, 1, 1, 4, 2, 4, 5, 0];
map[20] = [0, 4, 5, 1, 5, 1, 5, 1, 4, 5, 0, 0, 5, 4, 5, 1, 1, 4, 2, 4, 5, 0];
map[21] = [0, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0, 0, 5, 4, 5, 1, 1, 4, 2, 4, 5, 0];
map[22] = [0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 5, 5, 5, 4, 4, 4, 4, 4, 2, 4, 5, 0];
map[23] = [0, 4, 2, 2, 2, 4, 2, 2, 4, 4, 4, 4, 4, 4, 2, 2, 2, 2, 2, 4, 5, 0];
map[24] = [0, 4, 4, 4, 4, 4, 4, 4, 4, 5, 1, 1, 5, 4, 4, 4, 4, 4, 4, 4, 5, 0];
map[25] = [0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 0, 0, 5, 4, 5, 4, 2, 4, 5, 4, 5, 0];
map[26] = [0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 0, 0, 5, 4, 5, 4, 2, 4, 5, 4, 5, 0];
map[27] = [0, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0, 0, 5, 4, 5, 4, 2, 4, 5, 4, 5, 0];
map[28] = [0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 0, 0, 5, 4, 1, 4, 2, 4, 1, 4, 5, 0];
map[29] = [0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 0, 0, 5, 4, 5, 4, 2, 4, 5, 4, 5, 0];
map[30] = [0, 4, 5, 5, 5, 4, 5, 5, 4, 5, 0, 0, 5, 4, 5, 4, 2, 4, 5, 4, 5, 0];
map[31] = [0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 0, 0, 5, 4, 5, 4, 2, 4, 5, 4, 5, 0];
map[32] = [0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0];
map[33] = [0, 3, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 3, 3, 3, 5, 5, 5, 0];
map[34] = [0, 3, 3, 5, 5, 5, 5, 0, 0, 5, 5, 5, 5, 0, 0, 3, 3, 3, 5, 5, 5, 0];
map[35] = [0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0];


var WIDTH = document.getElementById("canvas").offsetWidth / 22;
var HEIGHT = document.getElementById("canvas").offsetHeight / 36;

var canvas = document.getElementById('canvas');
var context = canvas.getContext('2d');

var treeImage = new Image();
var brickImage = new Image();
var rockImage = new Image();
var userImage = new Image();
var waterImage = new Image();
var groundImage = new Image();

treeImage.src = "/images/tree.png";
brickImage.src = "/images/brick.png";
rockImage.src = "/images/rock.png";
userImage.src = "/images/user.png";
waterImage.src = "/images/water.png";
groundImage.src = "/images/ground.png";

var treeList = [];
var userList = [];
var waterList = [];

var selectedObject = {};

var javaServerHost = 'http://192.168.137.1:9999';