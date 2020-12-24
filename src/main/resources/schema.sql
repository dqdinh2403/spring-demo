create table if not exists Ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists Design (
    id identity,
    name varchar(50) not null
);

create table if not exists Design_Ingredients (
    design bigint not null,
    ingredient varchar(4) not null
);

alter table Design_Ingredients
    add foreign key (design) references Design(id);
alter table Design_Ingredients
    add foreign key (ingredient) references Ingredient(id);

create table if not exists TOrder (
    id identity,
    name varchar(50) not null,
    zip varchar(10) not null
);

create table if not exists TOrder_Designs (
    Torder bigint not null,
    design bigint not null
);

alter table TOrder_Designs
    add foreign key (Torder) references TOrder(id);
alter table TOrder_Designs
    add foreign key (design) references Design(id);