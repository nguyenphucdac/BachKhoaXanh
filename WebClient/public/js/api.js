
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
    getAPI(javaServerHost + '/get-list-thanh-vien', cFunction);
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
    getAPI(javaServerHost + '/get-thanh-vien/'+idThanhVien, cFunction);
}