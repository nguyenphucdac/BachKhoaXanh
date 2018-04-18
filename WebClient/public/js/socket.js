$( document ).ready(function() {
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
    
    socket.on("user move", function(data) {
        userMove(data);
    });
});
