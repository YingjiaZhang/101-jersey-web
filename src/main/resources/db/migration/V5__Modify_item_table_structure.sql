truncate item;
alter table item add categoryId int not null;
alter table  item add foreign key (categoryId) references category (id);