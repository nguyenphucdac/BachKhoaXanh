// map matrix
var map = [];
//         0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 
map[0] =  [0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0];
map[1] =  [0, 5, 5, 5, 5, 5, 5, 0, 0, 5, 5, 5, 5, 0, 0, 5, 5, 5, 5, 5, 5, 0];
map[2] =  [0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0];
map[3] =  [0, 4, 2, 2, 2, 2, 4, 2, 4, 5, 2, 2, 5, 4, 2, 2, 2, 2, 2, 4, 5, 0];
map[4] =  [0, 4, 4, 4, 4, 2, 4, 2, 4, 5, 0, 0, 5, 4, 2, 4, 4, 4, 2, 4, 5, 0];
map[5] =  [0, 4, 5, 1, 4, 2, 4, 2, 4, 5, 0, 0, 5, 4, 2, 4, 3, 4, 2, 4, 5, 0];
map[6] =  [0, 4, 5, 5, 4, 2, 4, 2, 4, 5, 0, 0, 5, 4, 2, 4, 5, 4, 2, 4, 5, 0];
map[7] =  [0, 4, 5, 1, 4, 2, 4, 2, 4, 5, 0, 0, 5, 4, 2, 4, 5, 4, 2, 4, 5, 0];
map[8] =  [0, 4, 5, 5, 4, 2, 4, 2, 4, 5, 0, 0, 5, 4, 2, 4, 1, 4, 2, 4, 5, 0];
map[9] =  [0, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 5, 0];
map[10] = [0, 4, 2, 2, 2, 2, 2, 2, 4, 5, 4, 4, 5, 4, 2, 2, 2, 2, 2, 2, 2, 0];
map[11] = [0, 4, 5, 2, 5, 5, 2, 5, 4, 5, 1, 1, 5, 4, 4, 4, 4, 4, 4, 3, 3, 0];
map[12] = [0, 4, 5, 2, 5, 5, 2, 5, 4, 5, 1, 1, 5, 4, 5, 5, 5, 5, 5, 3, 3, 0];
map[13] = [0, 4, 5, 2, 5, 5, 5, 5, 4, 5, 4, 4, 5, 4, 2, 2, 2, 2, 2, 2, 2, 0];
map[14] = [0, 4, 5, 2, 5, 5, 2, 5, 4, 5, 0, 0, 5, 4, 2, 2, 2, 2, 2, 2, 2, 0];
map[15] = [0, 4, 5, 2, 5, 5, 2, 5, 4, 5, 0, 0, 5, 4, 4, 4, 4, 4, 4, 4, 5, 0];
map[16] = [0, 4, 2, 2, 2, 2, 2, 2, 4, 5, 0, 0, 5, 4, 2, 2, 2, 2, 2, 4, 5, 0];
map[17] = [0, 4, 2, 2, 2, 2, 2, 2, 4, 5, 0, 0, 5, 4, 4, 4, 4, 4, 2, 4, 5, 0];
map[18] = [0, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0, 0, 5, 4, 5, 1, 1, 4, 2, 4, 5, 0];
map[19] = [0, 4, 1, 5, 1, 5, 1, 5, 4, 5, 0, 0, 5, 4, 5, 1, 1, 4, 2, 4, 5, 0];
map[20] = [0, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0, 0, 5, 4, 5, 1, 1, 4, 2, 4, 5, 0];
map[21] = [0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 4, 4, 5, 4, 4, 4, 4, 4, 2, 4, 5, 0];
map[22] = [0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 4, 4, 5, 4, 2, 2, 2, 2, 2, 4, 5, 0];
map[23] = [0, 4, 4, 4, 4, 4, 4, 4, 4, 5, 1, 1, 5, 4, 4, 4, 4, 4, 4, 4, 5, 0];
map[24] = [0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 0, 0, 5, 4, 5, 4, 2, 4, 5, 4, 5, 0];
map[25] = [0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 0, 0, 5, 4, 5, 4, 2, 4, 5, 4, 5, 0];
map[26] = [0, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0, 0, 5, 4, 5, 4, 2, 4, 5, 4, 5, 0];
map[27] = [0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 0, 0, 5, 4, 5, 4, 2, 4, 5, 4, 5, 0];
map[28] = [0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 0, 0, 5, 4, 1, 4, 2, 4, 1, 4, 5, 0];
map[29] = [0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0];
map[30] = [0, 3, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 3, 3, 0];
map[31] = [0, 3, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 3, 3, 0];
map[32] = [0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0];

// tree object


function waterObject() {

}

function userOnlineObject() {

}

var WIDTH = document.getElementById("canvas").offsetWidth/22;
var HEIGHT = document.getElementById("canvas").offsetHeight/34;

var canvas = document.getElementById('canvas');
var context = canvas.getContext('2d');

var treeImage = new Image();
var brickImage = new Image();
var rockImage = new Image();
var	userImage = new Image();
var waterImage = new Image();
var groundImage = new Image();

treeImage.src="/images/tree.png";
brickImage.src="/images/brick.png";
rockImage.src="/images/rock.png";
userImage.src="/images/user.png";
waterImage.src="/images/water.png";
groundImage.src="/images/ground.png";


var treeList = [];
var userList = [];
var waterList = [];

var selectedObject = {};

function getImageTree(idLoaiCay) {
    return new Image().src(idLoaiCay);
}

// API to get data from JAVA SERVER
function getAPI(uri, cFunction) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200){
          cFunction(this.responseText);
        }
      };
    xhttp.open("GET", uri, true);
    xhttp.send();
}

var javaServerHost = 'http://10.10.46.69:9999';
// API get tree list from java server
function loadTreeList(cFunction) {
    getAPI(javaServerHost + '/get-list-cay', cFunction);
}
// API get water list from java server
function loadWaterList(cFunction) {
    getAPI(javaServerHost + '/get-list-dcn', cFunction);
}
// API get user list from java server
function loadUserList(cFunction) {
    getAPI(javaServerHost + '/get-list-thanhvien', cFunction);
}
// API get one tree by Id from java server
function loadTree(cFunction, idCay) {
    getAPI(javaServerHost + '/get-cay/' + idCay, cFunction);
}
// API get one water by Id from java server
function loadWater(cFunction, idWater) {
    getAPI(javaServerHost + '/get-dcn/' + idWater, cFunction);
}
// API get one user by Id from java server
function loadUser(cFunction, idThanhVien) {
    getAPI(javaServerHost + '/get-thanhvien/'+idThanhVien, cFunction);
}

// function to get tree list, render tree list on display
function renderTreeList(data) {
   treeList = JSON.parse(data);
   drawTreeList();
}
// function to get water list, render water list on display
function renderWaterList(data) {
    waterList = JSON.parse(data);
    drawWaterList();
}
// function to get user list, render user list on display
function renderUserList(data) {
    userList = JSON.parse(data);
    drawUserList();
}
// funtion to draw tree list on canvas
function drawTreeList() {
    treeList.forEach(function(treeElement) {
        context.drawImage(treeImage, treeElement.toaDoY*WIDTH, treeElement.toaDoX*HEIGHT, WIDTH, HEIGHT);
    });
}
// function to draw water list on canvas
function drawWaterList() {
    waterList.forEach(function(water) {
        context.drawImage(waterImage, water.toaDoY*WIDTH, water.toaDoX*HEIGHT, WIDTH, HEIGHT);
    });
}
// function to draw user icon to canvas if user online
function drawUserList() {
    $ ("#user-list").text('');
    userList.forEach(function(user) {
        if (user.trangThai == 'on') {
            context.drawImage(userImage, user.toaDoY*WIDTH, user.toaDoX*HEIGHT, WIDTH, HEIGHT);
            $ ("#user-list").append("<li>" + user.tenDayDu + ":online</li>");
        } else {
            $ ("#user-list").append("<li>" + user.tenDayDu + ":offline</li>");
        }
    });
}
// function add tree and render on client tree when java server add tree
function addOneTree(data) {
    let newTree = JSON.parse(data);
    treeList.push(newTree);
    context.drawImage(treeImage, newTree.toaDoY*WIDTH, newTree.toaDoX*HEIGHT, WIDTH, HEIGHT);
    $ ("#notification-list").append("<li>" +newTree.tenCay+ " đã được thêm ở tọa độ x= " + newTree.toaDoY + ", y= "+ newTree.toaDoX + "</li>");
}

function editOneTree(data) {
    let updatedTree = JSON.parse(data);
    for (let i = 0; i < treeList.length; i++) {
        if (treeList[i].idCay == updatedTree.idCay) {
            context.drawImage(groundImage, treeList[i].toaDoY*WIDTH, treeList[i].toaDoX*HEIGHT, WIDTH, HEIGHT);
            context.drawImage(treeImage, updatedTree.toaDoY*WIDTH, updatedTree.toaDoX*HEIGHT, WIDTH, HEIGHT);
            // xử lý tưới ở đây
            treeList[i] = updatedTree;
            return;
        }
    }
}

function deleteOneTree(idCay) {
    for(let i =0; i < treeList.length; i++) {
        if (treeList[i].idCay == idCay) {
            context.drawImage(groundImage, treeList[i].toaDoY*WIDTH, treeList[i].toaDoX*HEIGHT, WIDTH, HEIGHT);
            treeList.splice(i, i+1);
            return;
        }
    }
}

// function add one water
function addOneWater(data) {
    let newWater = JSON.parse(data);
    waterList.push(newWater);
    context.drawImage(waterImage, newWater.toaDoY*WIDTH, newWater.toaDoX*HEIGHT, WIDTH, HEIGHT);
    $ ("#notification-list").append("<li>Điểm cấp nước id: " +waterList[i].idDiemCapNuoc+ " đã được thêm ở tọa độ x=" +waterList[i].toaDoY+ ", y="+waterList[i].toaDoX+ "</li>");
}

function editOneWater(data) {
    let updatedWater = JSON.parse(data);
    for (let i = 0; i < waterList.length; i++) {
        if (waterList[i].idDiemCapNuoc == updatedWater.idDiemCapNuoc) {
            context.drawImage(groundImage, waterList[i].toaDoY*WIDTH, waterList[i].toaDoX*HEIGHT, WIDTH, HEIGHT);
            context.drawImage(waterImage, updatedWater.toaDoY*WIDTH, updatedWater.toaDoX*HEIGHT, WIDTH, HEIGHT);
            waterList[i] = updatedWater;
            $ ("#notification-list").append("<li>Điểm cấp nước id: " +waterList[i].idDiemCapNuoc+ " đã được sửa</li>");
            return;
        }
    }
}

function deleteOneWater(idDiemCapNuoc) {
    for(let i =0; i < waterList.length; i++) {
        if (waterList[i].idDiemCapNuoc == idDiemCapNuoc) {
            context.drawImage(groundImage, waterList[i].toaDoY*WIDTH, waterList[i].toaDoX*HEIGHT, WIDTH, HEIGHT);
            waterList.splice(i, i+1);
            $ ("#notification-list").append("<li>Điểm cấp nước id: " +waterList[i].idDiemCapNuoc+ " đã bị xóa</li>");
            return;
        }
    }
}

// function handle when user login
function userLogin(idThanhVien) {
    userList.forEach(function(user) {
        if (user.idThanhVien == idThanhVien) {
            user.trangThai = 'on';
            drawUserList();
            $ ("#notification-list").append("<li>" +user.tenDayDu+ " đã đăng nhập</li>");
            return;
        }
    })
}
// function handle when user logout
function userLogout(idThanhVien) {
    userList.forEach(function(user) {
        if (user.idThanhVien == idThanhVien) {
            user.trangThai = 'off';
            drawUserList();
            $ ("#notification-list").append("<li>" +user.tenDayDu+ " đã đăng xuất</li>");
            return;
        }
    })
}

// function handle when user move
function userMove(data) {
    userList.forEach(function(user) {
        if (user.idThanhVien == data.idThanhVien){
            if (user.trangThai == 'on') {
                context.drawImage(groundImage,user.toaDoY*WIDTH, user.toaDoX*HEIGHT, WIDTH, HEIGHT);
                user.toaDoX = body.x;
                user.toaDoY = body.y;
                context.drawImage(userImage, user.toaDoY*WIDTH, user.toaDoX*HEIGHT, WIDTH, HEIGHT);
                return;
            }
        }
    });
}

function waterTree(idCay, luongNuoc) {

}

function processBarMove(start, end) {
    var elem = document.getElementById("myBar");   
    var width = start;
    var id = setInterval(frame, 10);
    function frame() {
      if (width >= end) {
        clearInterval(id);
      } else {
        width++; 
        elem.style.width = width + '%'; 
        elem.innerHTML = width * 1  + '%';
      }
    }
  }

// function render information of object (user || tree || water) on display
function renderInfo(obj) {
    if (!obj) {
        return;
    }
    if (obj.type == 'user') {

        $ ("#ex-image").text('');
        $ ("#ex-image").append('<img src="' + javaServerHost + obj.user.anhThanhVien +'">');

        $ ("#information").text('');
        $ ("#information").append('<div>Tên tài khoản:' + obj.user.tenTaiKhoan + '</div>');
        $ ("#information").append('<div>Tên đầy đủ:' + obj.user.tenDayDu + '</div>')
        $ ("#information").append('<div>Loại thành viên:' + obj.user.loaiThanhVien.tenLoaiThanhVien + '</div>')
    } else if (obj.type == 'tree') {
        $ ("#ex-image").text('');
        $ ("#ex-image").append('<img src="'+ javaServerHost + obj.tree.loaiCayObject.anhLoaiCay +'">');

        $ ("#information").text('');
        $ ("#information").append('<br><div id="myProgress"><div id="myBar"></div></div>')
        let processBarEnd = Math.floor(obj.tree.luongNuocDaTuoi/obj.tree.luongNuocToiDa*100);
        processBarMove(0, processBarEnd);
        $ ("#information").append('<div>Id cây:' + obj.tree.idCay + '</div>');
        $ ("#information").append('<div>Tên cây:' + obj.tree.tenCay + '</div>')
        $ ("#information").append('<div>Loại cây:' + obj.tree.loaiCayObject.tenLoaiCay + '</div>')
        $ ("#information").append('<div>Lượng nước tối đa:' + obj.tree.luongNuocToiDa + '</div>')
        $ ("#information").append('<div>Lượng nước đã tưới:' + obj.tree.luongNuocDaTuoi + '</div>')
        $ ("#information").append('<div>Tình trạng cây:' + obj.tree.tinhTrang + '</div>')

    } else if (obj.type == 'water') {
        $ ("#ex-image").text('');
        $ ("#ex-image").append('<img src="/images/water.png">');

        $ ("#information").text('');
        $ ("#information").append('<div>Id Điểm cấp nước:' + obj.water.idDiemCapNuoc + '</div>');
        $ ("#information").append('<div>Lượng nước tối đa:' + obj.water.luongNuocToiDa + '</div>')
        $ ("#information").append('<div>Tình trạng:' + obj.water.tinhTrang + '</div>')
    }
}

// function to render map from matrix
function renderMap() {
    for(let i = 0; i < map.length; i++) {
        for (let j = 0; j < map[i].length; j++) {
            let temp = map[i][j];
            if (temp == 0) {
                context.drawImage(brickImage, j*WIDTH, i*HEIGHT, WIDTH, HEIGHT);
            } 
            else if (temp == 2) {
                context.drawImage(rockImage, j*WIDTH, i*HEIGHT, WIDTH, HEIGHT);
            } else {
                context.drawImage(groundImage, j*WIDTH, i*HEIGHT, WIDTH, HEIGHT);
            }
        }
    }
    if (selectedObject) {
        drawBound(selectedObject.y, selectedObject.x);
    }
};
// function to draw a bound of selected object
function drawBound(x, y) {
    context.beginPath();
    context.lineWidth="1";
    context.strokeStyle="red";
    context.rect(x*WIDTH-1,y*HEIGHT-1,WIDTH+2,HEIGHT+2); 
    context.stroke();
}
// function update all map
function updateMap() {
    renderMap();
    drawTreeList();
    drawWaterList();
    drawUserList();
}

// function get an object when the mouse click into the canvas
function getMapObject(x, y) {
    for (let i = 0; i < userList.length; i++) {
        if (userList[i].toaDoX == x && userList[i].toaDoY == y) {
            selectedObject = {type: 'user',x: x, y: y, user: userList[i]};
            return selectedObject;
        }
    }
    for (let i = 0; i < treeList.length; i ++) {
        if (treeList[i].toaDoX == x && treeList[i].toaDoY == y) {
            selectedObject = {type: 'tree', x: x, y: y, tree: treeList[i]};
            return selectedObject;
        }
    }
    for (let i = 0; i < waterList.length; i++) {
        if (waterList[i].toaDoX == x && waterList[i].toaDoY == y) {
            selectedObject =  {type: 'water', x: x, y: y, water: waterList[i]};
            return selectedObject;
        }
    }
    return null;
}

$( document ).ready(function() {

    renderMap();
    loadTreeList(renderTreeList);
    loadWaterList(renderWaterList);
    loadUserList(renderUserList);

    socket = io();

    // socket listen on server add one tree
    socket.on("add tree", function(body) {
        loadTree(addOneTree, body.idCay);
    });

    // socket listen on server edit one tree
    socket.on('edit tree', function(body) {
        loadTree(editOneTree, body.idCay);
    });

    // socket listen on server 
    socket.on('delete tree', function(body) {
        deleteOneTree(body.idCay);
    });

    socket.on("add water", function(body) {
        loadWater(addOneWater, body.idDiemCapNuoc);
    });

    // socket listen on server edit one tree
    socket.on('edit water', function(body) {
        loadWater(editOneWater, body.idDiemCapNuoc);
    });

    // socket listen on server 
    socket.on('delete water', function(body) {
        deleteOneWater(body.idDiemCapNuoc);
    });

    // socket listen on 
    socket.on('user login', function(body) {
        userLogin(body.idThanhVien);
    });

    // socket listen on 
    socket.on('user logout', function(body) {
        userLogout(body.idThanhVien);
    });

    // socket listen on server add one water
    socket.on("add water", function(body) {
        loadWaterList(renderWaterList);
    });

    socket.on("user move", function(body) {
        userMove(body);
    });

    canvas.addEventListener('click', function(event) {
        let x = Math.floor(event.layerX/WIDTH);
        let y =  Math.floor(event.layerY/HEIGHT);
        renderInfo(getMapObject(y, x));
        updateMap();
    });

});	