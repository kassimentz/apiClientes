INSERT INTO tb_cliente(id, razao_social, telefone, endereco, data_cadastro, faturamento_declarado) VALUES ('879bfe49-d373-49ce-922c-bf4833543353', 'razao social 1', '51 999999999', 'endereco 1', '2023-07-24', '900.00');
INSERT INTO tb_cliente(id, razao_social, telefone, endereco, data_cadastro, faturamento_declarado) VALUES ('b5417dbe-e582-41dc-b8a0-af407b247030', 'razao social 2', '51 999498999', 'endereco 2', '2023-07-24', '1900.00');
INSERT INTO tb_cliente(id, razao_social, telefone, endereco, data_cadastro, faturamento_declarado) VALUES ('36cfbbf2-f8a9-4b0c-8d6f-5511c1d340f1', 'razao social 3', '51 999929939', 'endereco 3', '2023-07-24', '3900.00');

INSERT INTO tb_dados_bancarios(id, agencia, conta, banco, cliente_id) VALUES ('ae0fc710-191d-4db5-a759-d60327d73568', '0100', '88933-1', '001', '879bfe49-d373-49ce-922c-bf4833543353');
INSERT INTO tb_dados_bancarios(id, agencia, conta, banco, cliente_id) VALUES ('3fdb9490-e0c4-410c-a853-f8ffa38b374e', '8973', '0093-1', '003', '879bfe49-d373-49ce-922c-bf4833543353');
INSERT INTO tb_dados_bancarios(id, agencia, conta, banco, cliente_id) VALUES ('1be8ea1e-9745-4b39-ab48-4e2ad0828b10', '234', '91456-1', '005', '879bfe49-d373-49ce-922c-bf4833543353');

INSERT INTO tb_dados_bancarios(id, agencia, conta, banco, cliente_id) VALUES ('4ac97e21-261f-4b47-8dda-241378a977aa', '09873', '349032-1', '001', 'b5417dbe-e582-41dc-b8a0-af407b247030');
INSERT INTO tb_dados_bancarios(id, agencia, conta, banco, cliente_id) VALUES ('0020b30b-483e-4fba-97fd-90b027c6930a', '789', '783624-1', '004', 'b5417dbe-e582-41dc-b8a0-af407b247030');

INSERT INTO tb_dados_bancarios(id, agencia, conta, banco, cliente_id) VALUES ('dc28c50b-9278-4b96-99c0-91f82b01e7da', '945', '1320-1', '002', '36cfbbf2-f8a9-4b0c-8d6f-5511c1d340f1');