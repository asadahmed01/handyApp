const {
  create,
  findAll,
  findOne,
  retrieveAddress,
} = require("../controllers/seller.controller");

const router = require("express").Router();
router.get("/address/:id", retrieveAddress);
router.post("/createNew", create);
router.get("/getAll", findAll);
router.get("/:id", findOne);

module.exports = router;
