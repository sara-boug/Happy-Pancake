

create table if not exists PancakeOrder
(
   id  identity ,
   name varchar(30) not null,
   street varchar(30) not null,
   zipCode varchar(30) not null,
   state varchar(30) not null,
   city varchar(30) not null,
   creditCard varchar(30) not null
);
create table if not exists Pancake
(
  id   identity ,
    createAt Date not null,
   name varchar (30) not null
);
create table if not exists PancakeOrder_Pancake
(
   id_Pancake int not null,
   id_PancakeOrder int not null
);
alter table PancakeOrder_Pancake add foreign key (id_Pancake) references Pancake (id);
alter table PancakeOrder_Pancake add foreign key (id_PancakeOrder) references PancakeOrder (id);

create table if not exists Ingredient
(
  id int identity(1,1) primary key,
     name varchar(30) not null,
   types varchar(30) not null
);
create table if not exists Ingredient_Pancake
(
   id_Ingredient int not null,
   id_Pancake int not null
);
alter table Ingredient_Pancake add foreign key (id_Ingredient) references Ingredient (id);
alter table Ingredient_Pancake add foreign key (id_Pancake) references Pancake (id);