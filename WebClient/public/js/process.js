// function getImageTree(path) {
//     return new Image().src = javaServerHost + path;
// }

// function getImageUser(path) {
//     return new Image().src = javaServerHost + path;
// }

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

// funtion to draw tree list on canvas
function drawTreeList() {
    treeList.forEach(function (treeElement) {
        context.drawImage(treeImage, treeElement.toaDoY * WIDTH, treeElement.toaDoX * HEIGHT, WIDTH, HEIGHT);
    });
}
// function to draw water list on canvas
function drawWaterList() {
    waterList.forEach(function (water) {
        context.drawImage(waterImage, water.toaDoY * WIDTH, water.toaDoX * HEIGHT, WIDTH, HEIGHT);
    });
}

// function add tree and render on client tree when java server add tree
function addOneTree(data) {
    let newTree = JSON.parse(data);
    treeList.push(newTree);
    context.drawImage(treeImage, newTree.toaDoY * WIDTH, newTree.toaDoX * HEIGHT, WIDTH, HEIGHT);
    $("#notification-list").append("<li>" + newTree.tenCay + " đã được thêm ở tọa độ x= " + newTree.toaDoY + ", y= " + newTree.toaDoX + "</li>");
}

function editOneTree(data) {
    let updatedTree = JSON.parse(data);
    for (let i = 0; i < treeList.length; i++) {
        if (treeList[i].idCay == updatedTree.idCay) {
            context.drawImage(groundImage, treeList[i].toaDoY * WIDTH, treeList[i].toaDoX * HEIGHT, WIDTH, HEIGHT);
            context.drawImage(treeImage, updatedTree.toaDoY * WIDTH, updatedTree.toaDoX * HEIGHT, WIDTH, HEIGHT);
            // xử lý tưới ở đây
            treeList[i] = updatedTree;
            return;
        }
    }
}

function deleteOneTree(idCay) {
    for (let i = 0; i < treeList.length; i++) {
        if (treeList[i].idCay == idCay) {
            context.drawImage(groundImage, treeList[i].toaDoY * WIDTH, treeList[i].toaDoX * HEIGHT, WIDTH, HEIGHT);
            treeList.splice(i, i + 1);
            return;
        }
    }
}

// function add one water
function addOneWater(data) {
    let newWater = JSON.parse(data);
    waterList.push(newWater);
    context.drawImage(waterImage, newWater.toaDoY * WIDTH, newWater.toaDoX * HEIGHT, WIDTH, HEIGHT);
    $("#notification-list").append("<li>Điểm cấp nước id: " + waterList[i].idDiemCapNuoc + " đã được thêm ở tọa độ x=" + waterList[i].toaDoY + ", y=" + waterList[i].toaDoX + "</li>");
}

function editOneWater(data) {
    let updatedWater = JSON.parse(data);
    for (let i = 0; i < waterList.length; i++) {
        if (waterList[i].idDiemCapNuoc == updatedWater.idDiemCapNuoc) {
            context.drawImage(groundImage, waterList[i].toaDoY * WIDTH, waterList[i].toaDoX * HEIGHT, WIDTH, HEIGHT);
            context.drawImage(waterImage, updatedWater.toaDoY * WIDTH, updatedWater.toaDoX * HEIGHT, WIDTH, HEIGHT);
            waterList[i] = updatedWater;
            $("#notification-list").append("<li>Điểm cấp nước id: " + waterList[i].idDiemCapNuoc + " đã được sửa</li>");
            return;
        }
    }
}

function deleteOneWater(idDiemCapNuoc) {
    for (let i = 0; i < waterList.length; i++) {
        if (waterList[i].idDiemCapNuoc == idDiemCapNuoc) {
            context.drawImage(groundImage, waterList[i].toaDoY * WIDTH, waterList[i].toaDoX * HEIGHT, WIDTH, HEIGHT);
            waterList.splice(i, i + 1);
            $("#notification-list").append("<li>Điểm cấp nước id: " + waterList[i].idDiemCapNuoc + " đã bị xóa</li>");
            return;
        }
    }
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
            elem.innerHTML = width * 1 + '%';
        }
    }
}

// function render information of object (user || tree || water) on display
function renderInfo(obj) {
    if (!obj) {
        return;
    }
    if (obj.type == 'user') {

        $("#ex-image").text('');
        $("#ex-image").append('<img src="' + javaServerHost + obj.user.anhThanhVien + '">');

        $("#information").text('');
        $("#information").append('<div>Tên tài khoản:' + obj.user.tenTaiKhoan + '</div>');
        $("#information").append('<div>Tên đầy đủ:' + obj.user.tenDayDu + '</div>')
        $("#information").append('<div>Loại thành viên:' + obj.user.loaiThanhVien.tenLoaiThanhVien + '</div>')
    } else if (obj.type == 'tree') {
        $("#ex-image").text('');
        $("#ex-image").append('<img src="' + javaServerHost + obj.tree.loaiCayObject.anhLoaiCay + '">');

        $("#information").text('');
        $("#information").append('<br><div id="myProgress"><div id="myBar"></div></div>')
        let processBarEnd = Math.floor(obj.tree.luongNuocDaTuoi / obj.tree.luongNuocToiDa * 100);
        processBarMove(0, processBarEnd);
        $("#information").append('<div>Id cây:' + obj.tree.idCay + '</div>');
        $("#information").append('<div>Tên cây:' + obj.tree.tenCay + '</div>')
        $("#information").append('<div>Loại cây:' + obj.tree.loaiCayObject.tenLoaiCay + '</div>')
        $("#information").append('<div>Lượng nước tối đa:' + obj.tree.luongNuocToiDa + '</div>')
        $("#information").append('<div>Lượng nước đã tưới:' + obj.tree.luongNuocDaTuoi + '</div>')
        $("#information").append('<div>Tình trạng cây:' + obj.tree.tinhTrang + '</div>')

    } else if (obj.type == 'water') {
        $("#ex-image").text('');
        $("#ex-image").append('<img src="/images/water.png">');

        $("#information").text('');
        $("#information").append('<div>Id Điểm cấp nước:' + obj.water.idDiemCapNuoc + '</div>');
        $("#information").append('<div>Lượng nước tối đa:' + obj.water.luongNuocToiDa + '</div>')
        $("#information").append('<div>Tình trạng:' + obj.water.tinhTrang + '</div>')
    }
}

// function to render map from matrix
function renderMap() {
    for (let i = 0; i < map.length; i++) {
        for (let j = 0; j < map[i].length; j++) {
            let temp = map[i][j];
            if (temp == 0) {
                context.drawImage(brickImage, j * WIDTH, i * HEIGHT, WIDTH, HEIGHT);
            } else if (temp == 2) {
                context.drawImage(rockImage, j * WIDTH, i * HEIGHT, WIDTH, HEIGHT);
            } else {
                context.drawImage(groundImage, j * WIDTH, i * HEIGHT, WIDTH, HEIGHT);
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
    context.lineWidth = "1";
    context.strokeStyle = "red";
    context.rect(x * WIDTH + 1, y * HEIGHT + 1, WIDTH - 2, HEIGHT - 2);
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
        if (userList[i].toaDoX == x && userList[i].toaDoY == y && userList[i].trangThai == 'on') {
            selectedObject = {
                type: 'user',
                x: x,
                y: y,
                user: userList[i]
            };
            return selectedObject;
        }
    }
    for (let i = 0; i < treeList.length; i++) {
        if (treeList[i].toaDoX == x && treeList[i].toaDoY == y) {
            selectedObject = {
                type: 'tree',
                x: x,
                y: y,
                tree: treeList[i]
            };
            return selectedObject;
        }
    }
    for (let i = 0; i < waterList.length; i++) {
        if (waterList[i].toaDoX == x && waterList[i].toaDoY == y) {
            selectedObject = {
                type: 'water',
                x: x,
                y: y,
                water: waterList[i]
            };
            return selectedObject;
        }
    }
    return null;
}

$(document).ready(function () {

    renderMap();
    loadTreeList(renderTreeList);
    loadWaterList(renderWaterList);
    loadUserList(renderUserList);

    canvas.addEventListener('click', function (event) {
        let x = Math.floor(event.layerX / WIDTH);
        let y = Math.floor(event.layerY / HEIGHT);
        renderInfo(getMapObject(y, x));
        updateMap();
    });

});