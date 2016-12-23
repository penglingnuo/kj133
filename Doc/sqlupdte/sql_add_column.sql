--修改Locator表，新增webx，weby字段
alter table Locator add webx int  NULL
alter table Locator add weby int  NULL

--修改CardReader表，新增webx，weby字段
alter table CardReader add webx int  NULL
alter table CardReader add weby int  NULL