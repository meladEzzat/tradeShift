DROP TABLE IF EXISTS node;

CREATE TABLE node (
  id VARCHAR(250)  PRIMARY KEY,
  parent_id VARCHAR(250) ,
  root_id VARCHAR(250) ,
  height INT
);

INSERT INTO node (id, parent_id, root_id,height) VALUES
  ('54e4b3f2-850d-11eb-8dcd-0242ac130003', null, null,0),
  ('9690437a-850d-11eb-8dcd-0242ac130003', '54e4b3f2-850d-11eb-8dcd-0242ac130003', '54e4b3f2-850d-11eb-8dcd-0242ac130003',1),
  ('fe87kjre-850d-11eb-8dcd-0242ac130003', '54e4b3f2-850d-11eb-8dcd-0242ac130003', '54e4b3f2-850d-11eb-8dcd-0242ac130003',1),
  ('d0388d08-850d-11eb-8dcd-0242ac130003', '9690437a-850d-11eb-8dcd-0242ac130003', '54e4b3f2-850d-11eb-8dcd-0242ac130003',2);
