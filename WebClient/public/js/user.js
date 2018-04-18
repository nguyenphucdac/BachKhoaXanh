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

// function to get user list, render user list on display
function renderUserList(data) {
    userList = JSON.parse(data);
    drawUserList();
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
        if (user.idThanhVien == data.id){
            if (user.trangThai == 'on') {
                context.drawImage(groundImage,user.toaDoY*WIDTH, user.toaDoX*HEIGHT, WIDTH, HEIGHT);
                user.toaDoX = data.x;
                user.toaDoY = data.y;
                context.drawImage(userImage, user.toaDoY*WIDTH, user.toaDoX*HEIGHT, WIDTH, HEIGHT);
                if (selectedObject.type == 'user' && selectedObject.user.idThanhVien == user.idThanhVien) {
                    selectedObject.x = user.toaDoX;
                    selectedObject.y = user.toaDoY;
                    selectedObject.user = user;
                    drawBound(user.toaDoY, user.toaDoX);
                }
                return;
            }
        }
    });
}