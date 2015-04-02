
    drop table fo_add_cost cascade constraints;

    drop table fo_address cascade constraints;

    drop table fo_contact cascade constraints;

    drop table fo_dish cascade constraints;

    drop table fo_dish_comp cascade constraints;

    drop table fo_dish_elem cascade constraints;

    drop table fo_dish_genere cascade constraints;

    drop table fo_dish_price cascade constraints;

    drop table fo_email cascade constraints;

    drop table fo_food_order cascade constraints;

    drop table fo_genere cascade constraints;

    drop table fo_order_item cascade constraints;

    drop table fo_person cascade constraints;

    drop table fo_phone cascade constraints;

    drop table fo_provider cascade constraints;

    drop table fo_vacation cascade constraints;

    drop table fo_web_url cascade constraints;

    drop table fo_working_hour cascade constraints;

    drop sequence seq_fo_add_cost_id;

    drop sequence seq_fo_address_id;

    drop sequence seq_fo_contact_id;

    drop sequence seq_fo_dish_comp_id;

    drop sequence seq_fo_dish_elem_id;

    drop sequence seq_fo_dish_genere_id;

    drop sequence seq_fo_dish_id;

    drop sequence seq_fo_dish_price_id;

    drop sequence seq_fo_email_id;

    drop sequence seq_fo_food_ord_id;

    drop sequence seq_fo_genere_id;

    drop sequence seq_fo_ord_item_id;

    drop sequence seq_fo_person_id;

    drop sequence seq_fo_phone_id;

    drop sequence seq_fo_provider_id;

    drop sequence seq_fo_sub_ord_item_id;

    drop sequence seq_fo_vacation_id;

    drop sequence seq_fo_web_url_id;

    drop sequence seq_fo_working_hours_id;

    create table fo_add_cost (
        id number(19,0) not null,
        cost number(19,2),
        kind varchar2(15 char) not null,
        last_modification_id number(19,0),
        status varchar2(1 char) not null,
        provider_id number(19,0),
        primary key (id)
    );

    create table fo_address (
        id number(19,0) not null,
        address1 varchar2(255 char),
        address2 varchar2(255 char),
        city varchar2(255 char),
        comment_text varchar2(1024 char),
        country varchar2(255 char),
        display_index number(10,0) not null,
        last_modification_id number(19,0),
        state varchar2(40 char),
        status varchar2(1 char) not null,
        zip_code varchar2(15 char),
        primary key (id)
    );

    create table fo_contact (
        id number(19,0) not null,
        last_modification_id number(19,0),
        status varchar2(1 char) not null,
        primary key (id)
    );

    create table fo_dish (
        id number(19,0) not null,
        end_day timestamp not null,
        start_day timestamp not null,
        description varchar2(255 char),
        last_modification_id number(19,0),
        name varchar2(255 char),
        status varchar2(1 char) not null,
        provider_id number(19,0),
        primary key (id)
    );

    create table fo_dish_comp (
        id number(19,0) not null,
        description varchar2(255 char),
        last_modification_id number(19,0),
        max_elements number(10,0) not null,
        elements_required number(10,0) not null,
        status varchar2(1 char) not null,
        use_as_dish_price varchar2(1 char) not null,
        dish_id number(19,0),
        primary key (id)
    );

    create table fo_dish_elem (
        id number(19,0) not null,
        description varchar2(255 char),
        last_modification_id number(19,0),
        name varchar2(255 char),
        status varchar2(1 char) not null,
        dish_comp_id number(19,0),
        primary key (id)
    );

    create table fo_dish_genere (
        id number(19,0) not null,
        last_modification_id number(19,0),
        dish_id number(19,0),
        genere_id number(19,0),
        primary key (id)
    );

    create table fo_dish_price (
        id number(19,0) not null,
        cost number(19,2),
        last_modification_id number(19,0),
        last_upd_time timestamp not null,
        dish_id number(19,0),
        dish_comp_id number(19,0),
        dish_elem_id number(19,0),
        primary key (id)
    );

    create table fo_email (
        id number(19,0) not null,
        comment_text varchar2(1024 char),
        display_index number(10,0) not null,
        email_address varchar2(255 char) not null,
        last_modification_id number(19,0),
        status varchar2(1 char) not null,
        contact_id number(19,0) not null,
        primary key (id)
    );

    create table fo_food_order (
        id number(19,0) not null,
        last_modification_id number(19,0),
        add_time timestamp not null,
        order_status varchar2(1 char) not null,
        order_time timestamp not null,
        person_id number(19,0),
        provider_id number(19,0),
        primary key (id)
    );

    create table fo_genere (
        id number(19,0) not null,
        last_modification_id number(19,0),
        name varchar2(255 char),
        primary key (id)
    );

    create table fo_order_item (
        id number(19,0) not null,
        count number(10,0) not null,
        last_modification_id number(19,0),
        dish_id number(19,0),
        food_order_id number(19,0),
        person_id number(19,0),
        dish_comp_id number(19,0),
        dish_elem_id number(19,0),
        order_item_id number(19,0),
        primary key (id)
    );

    create table fo_person (
        id number(19,0) not null,
        last_modification_id number(19,0),
        login varchar2(255 char) not null,
        password varchar2(255 char) not null,
        primary key (id)
    );

    create table fo_phone (
        id number(19,0) not null,
        comment_text varchar2(1024 char),
        display_index number(10,0) not null,
        last_modification_id number(19,0),
        number_type varchar2(1 char) not null,
        phone varchar2(15 char),
        phone_ext varchar2(15 char),
        status varchar2(1 char) not null,
        contact_id number(19,0) not null,
        primary key (id)
    );

    create table fo_provider (
        id number(19,0) not null,
        comment varchar2(255 char) not null,
        last_modification_id number(19,0),
        name varchar2(255 char) not null,
        status varchar2(1 char) not null,
        address_id number(19,0),
        contact_id number(19,0),
        primary key (id)
    );

    create table fo_vacation (
        owner_type varchar2(1 char) not null,
        id number(19,0) not null,
        end_day timestamp not null,
        last_modification_id number(19,0),
        start_day timestamp not null,
        provider_id number(19,0),
        primary key (id)
    );

    create table fo_web_url (
        id number(19,0) not null,
        comment_text varchar2(1024 char),
        display_index number(10,0) not null,
        last_modification_id number(19,0),
        status varchar2(1 char) not null,
        web_url_address varchar2(255 char) not null,
        contact_id number(19,0) not null,
        primary key (id)
    );

    create table fo_working_hour (
        owner_type varchar2(1 char) not null,
        id number(19,0) not null,
        end_time timestamp not null,
        last_modification_id number(19,0),
        start_time timestamp not null,
        week_day varchar2(15 char) not null,
        provider_id number(19,0),
        primary key (id)
    );

    create index idx_add_cost_provider_fk on fo_add_cost (provider_id);

    create index idx_dish_provider_fk on fo_dish (provider_id);

    create index idx_dish_comp_dish_fk on fo_dish_comp (dish_id);

    create index idx_dish_elem_dish_comp_fk on fo_dish_elem (dish_comp_id);

    create index idx_dish_gene_dish_fk on fo_dish_genere (dish_id);

    create index idx_dish_gene_gene_fk on fo_dish_genere (genere_id);

    create index idx_dish_price_dish_fk on fo_dish_price (dish_id);

    create index idx_dish_price_dish_comp_fk on fo_dish_price (dish_comp_id);

    create index idx_dish_price_dish_elem_fk on fo_dish_price (dish_elem_id);

    create index idx_contact_email_fk on fo_email (contact_id);

    create index idx_food_order_person_fk on fo_food_order (person_id);

    create index idx_food_order_provider_fk on fo_food_order (provider_id);

    create index idx_order_time on fo_food_order (order_time);

    create index idx_order_item_person_fk on fo_order_item (person_id);

    create index idx_order_item_food_ord_fk on fo_order_item (food_order_id);

    create index idx_order_item_dish_fk on fo_order_item (dish_id);

    create index idx_contact_phone_fk on fo_phone (contact_id);

    create index idx_provider_name on fo_provider (name);

    create index idx_provider_address_fk on fo_provider (address_id);

    create index idx_provider_contact_fk on fo_provider (contact_id);

    create index idx_vacation_provider_fk on fo_vacation (provider_id);

    create index idx_contact_web_url_fk on fo_web_url (contact_id);

    create index idx_wrk_hour_provider_fk on fo_working_hour (provider_id);

    alter table fo_add_cost 
        add constraint add_cost_provider_fk 
        foreign key (provider_id) 
        references fo_provider;

    alter table fo_dish 
        add constraint dish_provider_fk 
        foreign key (provider_id) 
        references fo_provider;

    alter table fo_dish_comp 
        add constraint dish_comp_dish_fk 
        foreign key (dish_id) 
        references fo_dish;

    alter table fo_dish_elem 
        add constraint dish_elem_dish_comp_fk 
        foreign key (dish_comp_id) 
        references fo_dish_comp;

    alter table fo_dish_genere 
        add constraint dish_gene_dish_fk 
        foreign key (dish_id) 
        references fo_dish;

    alter table fo_dish_genere 
        add constraint dish_gene_gene_fk 
        foreign key (genere_id) 
        references fo_genere;

    alter table fo_dish_price 
        add constraint dish_price_dish_fk 
        foreign key (dish_id) 
        references fo_dish;

    alter table fo_dish_price 
        add constraint dish_price_dish_comp_fk 
        foreign key (dish_comp_id) 
        references fo_dish_comp;

    alter table fo_dish_price 
        add constraint dish_price_dish_elem_fk 
        foreign key (dish_elem_id) 
        references fo_dish_elem;

    alter table fo_email 
        add constraint FK_bu7amv53jcvjdrtfeqv09w99s 
        foreign key (contact_id) 
        references fo_contact;

    alter table fo_food_order 
        add constraint food_order_person_fk 
        foreign key (person_id) 
        references fo_person;

    alter table fo_food_order 
        add constraint food_order_provider_fk 
        foreign key (provider_id) 
        references fo_provider;

    alter table fo_order_item 
        add constraint order_item_dish_fk 
        foreign key (dish_id) 
        references fo_dish;

    alter table fo_order_item 
        add constraint order_item_food_ord_fk 
        foreign key (food_order_id) 
        references fo_food_order;

    alter table fo_order_item 
        add constraint order_item_person_fk 
        foreign key (person_id) 
        references fo_person;

    alter table fo_order_item 
        add constraint ord_sub_item_dish_comp_fk 
        foreign key (dish_comp_id) 
        references fo_dish_comp;

    alter table fo_order_item 
        add constraint ord_sub_item_dish_elem_fk 
        foreign key (dish_elem_id) 
        references fo_dish_elem;

    alter table fo_order_item 
        add constraint ord_sub_item_ord_item_fk 
        foreign key (order_item_id) 
        references fo_order_item;

    alter table fo_phone 
        add constraint FK_jes52mwkd3q1ccscxsh361cid 
        foreign key (contact_id) 
        references fo_contact;

    alter table fo_provider 
        add constraint provider_address_fk 
        foreign key (address_id) 
        references fo_address;

    alter table fo_provider 
        add constraint provider_contact_fk 
        foreign key (contact_id) 
        references fo_contact;

    alter table fo_vacation 
        add constraint vacation_provider_fk 
        foreign key (provider_id) 
        references fo_provider;

    alter table fo_web_url 
        add constraint FK_kcetlus2967vaxqflmv9asjfv 
        foreign key (contact_id) 
        references fo_contact;

    alter table fo_working_hour 
        add constraint wrk_hour_provider_fk 
        foreign key (provider_id) 
        references fo_provider;

    create sequence seq_fo_add_cost_id start with 1 increment by 1;

    create sequence seq_fo_address_id start with 1 increment by 1;

    create sequence seq_fo_contact_id start with 1 increment by 1;

    create sequence seq_fo_dish_comp_id start with 1 increment by 1;

    create sequence seq_fo_dish_elem_id start with 1 increment by 1;

    create sequence seq_fo_dish_genere_id start with 1 increment by 1;

    create sequence seq_fo_dish_id start with 1 increment by 1;

    create sequence seq_fo_dish_price_id start with 1 increment by 1;

    create sequence seq_fo_email_id start with 1 increment by 1;

    create sequence seq_fo_food_ord_id start with 1 increment by 1;

    create sequence seq_fo_genere_id start with 1 increment by 1;

    create sequence seq_fo_ord_item_id start with 1 increment by 1;

    create sequence seq_fo_person_id start with 1 increment by 1;

    create sequence seq_fo_phone_id start with 1 increment by 1;

    create sequence seq_fo_provider_id start with 100 increment by 1;

    create sequence seq_fo_sub_ord_item_id start with 1 increment by 1;

    create sequence seq_fo_vacation_id start with 1 increment by 1;

    create sequence seq_fo_web_url_id start with 1 increment by 1;

    create sequence seq_fo_working_hours_id start with 1 increment by 1;
