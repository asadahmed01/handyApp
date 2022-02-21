const router = require("express").Router();

const {
  findAll,
  create,
  findOne,
  update,
  retrieveAddress,
} = require("../controllers/customer.controller.js");
const userMiddleware = require("../middleware/users.js");

router.get("/getAll", findAll);
router.post("/createNew", userMiddleware.validateRegister, create);
//get address
router.get("/address/:id", retrieveAddress);
// Retrieve a single Customer with id
router.post("/login", findOne);
// Update a Customer with id
router.put("/:id", update);
module.exports = router;
