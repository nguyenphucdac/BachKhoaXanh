
// Setup basic express server
var express = require('express');
var app = express();
var request = require('request');
var path = require('path');
var server = require('http').createServer(app);
var io = require('socket.io')(server);
var port = process.env.PORT || 3000;
var bodyParser = require('body-parser');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));


// Set the headers
var headers = {
  'Content-Type': 'application/x-www-form-urlencoded'
}
var options = {
  url: 'http://localhost:3030/test',
  method: 'GET',
  headers: headers,
}
request(options, function (error, response, body) {
  if (!error && response.statusCode == 200) {
      // Print out the response body
      console.log(response.body)
  }
})

server.listen(port, function () {
  console.log('Server listening at port %d', port);
});

// Routing
app.use(express.static(path.join(__dirname, 'public')));

app.get('/manager', (req, res) => {
  res.sendFile(__dirname + '/public/index.html');
}) 

// CORS

// app.use(function(req, res, next) {
//   res.header("Access-Control-Allow-Origin", *);
//   res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, authorization");
//   res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//   next();
// });

var clients = [];

io.on('connection', function (socket) {
  console.log(socket.handshake.headers.host + ' connected')
  var addedUser = false;
  // when the client emits 'new message', this listens and executes

  // when the client emits 'add user', this listens and executes
  socket.on('add user', function (user) {
    if (addedUser) return;

    // we store the username in the socket session for this client
    socket.user = user;
    addedUser = true;

    //
    var joined = false;
    for (var key in clients) {
      if (key == user.username) {
        clients[key]++;
        joined = true;
      }
    }
    if (!joined) {
      clients[user.username] = 1;
      // echo globally (all clients) that a person has connected
      socket.broadcast.emit('user joined', user);
    }
  });

  // API Socket

  app.post('/add-tree', (req, res) => {
    console.log(req.body);
    socket.broadcast.emit('add tree', req.body);
    res.status(200).json({status: 200, message: "Successfully!"});
  });
  app.post('/edit-tree', (req, res) => {
    socket.broadcast.emit('edit tree', req.body);
    res.status(200).json({status: 200, message: "Successfully!"});
  });
  app.post('/delete-tree', (req, res) => {
    socket.broadcast.emit('add tree', req.body);
    res.status(200).json({status: 200, message: "Successfully!"});
  });

  app.post('/add-water', (req, res) => {
    socket.broadcast.emit('add water', req.body);
    res.status(200).json({status: 200, message: "Successfully!"});
  });
  app.post('/edit-water', (req, res) => {
    socket.broadcast.emit('edit water', req.body);
    res.status(200).json({status: 200, message: "Successfully!"});
  });
  app.post('/delete-water', (req, res) => {
    socket.broadcast.emit('delete water', req.body);
    res.status(200).json({status: 200, message: "Successfully!"});
  });

  app.post('/login', (req, res) => {
    // process user move here
    socket.broadcast.emit('user login', req.body);
    res.status(200).json({status: 200, message: "Successfully!"});  
  });
 
  app.post('/logout', (req, res) => {
    // process user move here
    socket.broadcast.emit('user logout', req.body);
    res.status(200).json({status: 200, message: "Successfully!"});  
  });
  socket.on('user move', function(id, x, y) {
    let data = {
      id: id,
      x: x,
      y: y
    }
    socket.broadcast.emit('user move', data);
  });

  // when the user disconnects.. perform this
  socket.on('disconnect', function () {
    socket.handshake.headers.host + ' disconnected'
    if (addedUser) {
      clients[socket.user.username]--;
      if (clients[socket.user.username] == 0) {
        // echo globally that this client has left
        socket.broadcast.emit('user left', socket.user);
      }
    }
  });
});
