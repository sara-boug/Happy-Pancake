create table if not exists Users(
  id identity, 
  fullname varchar(100) not null, 
  email varchar(100) not null , 
  phonenumber varchar(20) not  null,
  password varchar(300) not null 
  
);

create table if not exists PancakeOrder
(
   id  identity ,
   name varchar(100) not null,
   street varchar(100) not null,
   zipCode varchar(100) not null,
   state varchar(100) not null,
   city varchar(100) not null,
   creditCard varchar(100) not null,
   id_Users int not null
   
);
alter table PancakeOrder add foreign key (id_Users) references Users(id); 

create table if not exists Pancake
(
    id identity ,
    createAt Date not null,
    name varchar (100) not null
    
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
     name varchar(100) not null,
   types varchar(100) not null
);
create table if not exists Ingredient_Pancake
(
   id_Ingredient int not null,
   id_Pancake int not null
);
alter table Ingredient_Pancake add foreign key (id_Ingredient) references Ingredient (id);
alter table Ingredient_Pancake add foreign key (id_Pancake) references Pancake (id);