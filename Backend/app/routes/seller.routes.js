const {
  create,
  findAll,
  findOne,
} = require("../controllers/seller.controller");

const router = require("express").Router();

router.post("/createNew", create);
router.get("/getAll", findAll);
router.get("/:id", findOne);

module.exports = router;
