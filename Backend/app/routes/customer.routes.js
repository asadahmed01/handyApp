const router = require("express").Router();
const bcrypt = require("bcrypt");

const {
  findAll,
  create,
  findOne,
  update,
} = require("../controllers/customer.controller.js");

router.get("/getAll", findAll);
router.post("/createNew", create);

// Retrieve a single Customer with id
router.post("/login", findOne);
// Update a Customer with id
router.put("/:id", update);

module.exports = router;
