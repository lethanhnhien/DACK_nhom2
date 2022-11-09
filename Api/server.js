const fs = require('fs')
const bodyParser = require('body-parser')
const jsonServer = require('json-server')
const jwt = require('jsonwebtoken')

const server = jsonServer.create()
const router = jsonServer.router('./database/db.json')
const userdb = JSON.parse(fs.readFileSync('./database/users.json', 'UTF-8'))
const db = JSON.parse(fs.readFileSync('./database/db.json', 'UTF-8'))

server.use(bodyParser.urlencoded({ extended: true }))
server.use(bodyParser.json())
server.use(jsonServer.defaults());

const SECRET_KEY = '123456789'

const expiresIn = '1h'

// Create a token from a payload 
function createToken(payload) {
  return jwt.sign(payload, SECRET_KEY, { expiresIn })
}

// Verify the token 
function verifyToken(token) {
  return jwt.verify(token, SECRET_KEY, (err, decode) => decode !== undefined ? decode : err)
}

// Check if the user exists in database
function isAuthenticated({ email, password }) {
  return userdb.users.findIndex(user => user.email === email && user.password === password) !== -1
}
//check if the email exists in database
function isEmailExited({ email }) {
  return userdb.users.findIndex(user => user.email === email) !== -1
}
//find user by email
function getUserByEmail(email) {
  return userdb.users.find(user => user.email === email)
}
// Register New User
server.post('/auth/register', (req, res) => {
  const { email, password, name } = req.body;

  fs.readFile("./database/users.json", (err, data) => {
    if (err) {
      const status = 401
      const message = err
      res.status(status).json({ status, message })
      return
    };

    // Get current users data
    var data = JSON.parse(data.toString());
    console.log(" data >>> ", err, data)
    // Get the id of last user
    var last_item_id = data.users[data.users.length - 1].id;

    if (isEmailExited(email)) {
      const status = 401;
      const message = 'Email already exist';
      res.status(status).json({ status, message });
      return
    } else {
      data.users.push({ id: last_item_id + 1, name: name, email: email, password: password }); //add some data
      var writeData = fs.writeFile("./database/users.json", JSON.stringify(data), (err, result) => {  // WRITE
        if (err) {
          const status = 401
          const message = err
          return  res.status(status).json({ status, message })
        }
        return res.status(200).json({ status: 200, message: "Đăng kí thành công" })
      }); 
    }
  });
})

// Login to one of the users from ./users.json
server.post('/auth/login', (req, res) => {
  const { email, password } = req.body;

  // if ({email, password}) === false) {
  //   const status = 401
  //   const message = 'Incorrect email or password'
  //   res.status(status).json({status, message})
  //   return
  // }

 

  const access_token = createToken({ email, password })
  console.log("Access Token:" + access_token);
  res.status(200).json({ access_token })
})
// GET all order
server.get('/auth/orders', (req, res, next) => {
  const status = 200
  res.status(status).json(db.orders)
  return
})
// GET One order
server.get('/auth/orders/:id', (req, res, next) => {
  const id = req.params.id;
  console.log(id)
  const status = 200
  res.status(status).json(db.orders.find(item => item.codeOrder == id))
  return
})
// POST Add order
server.post('/auth/orders/', (req, res, next) => {
  const item = req.body;
  console.log(item)
  const status = 200
  res.status(status).json(db.orders.push(item))
  return
})
// DELETE Delete order
server.delete('/auth/orders/:codeOrder', (req, res, next) => {
  const codeOrder = req.params.codeOrder;
  console.log(codeOrder)
  const status = 200
  const err = 404
  let item = db.orders.find(item => item.codeOrder == codeOrder)
  if (item) {
    const index = db.orders.indexOf(item);
    if (index > -1) { // only splice array when item is found
      db.orders.splice(index, 1); // 2nd parameter means remove one item only
    }
    res.status(status).json(item)
  } else {
    res.status(err).json(item)
  }
  return
})
// PUT Update order
server.put('/auth/orders/:codeOrder', (req, res, next) => {
  const codeOrder = req.params.codeOrder;
  const status = 200
  const err = 404
  let item = db.orders.find(item => item.codeOrder == codeOrder)
  let newItem = req.body
  if (item) {
    const index = db.orders.indexOf(item);
    if (index > -1) {
      console.log(item)
      db.orders[index] = newItem;
      res.status(status).json(db.orders[index])
    }

  } else {
    res.status(err).json(item)
  }
  return
})
server.use(/(\/auth).*$/, (req, res, next) => {
  if (req.headers.authorization === undefined || req.headers.authorization.split(' ')[0] !== 'Bearer') {
    const status = 401
    const message = 'Error in authorization format'
    res.status(status).json({ status, message })
    return
  }
  try {
    let verifyTokenResult;
    verifyTokenResult = verifyToken(req.headers.authorization.split(' ')[1]);

    if (verifyTokenResult instanceof Error) {
      const status = 401
      const message = 'Access token not provided'
      res.status(status).json({ status, message })
      return
    }
    next()
  } catch (err) {
    const status = 401
    const message = 'Error access_token is revoked'
    res.status(status).json({ status, message })
  }
})

server.use(router)

server.listen(3000, () => {
  console.log('Run Auth API Server')
})