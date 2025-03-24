

CREATE TABLE public.biller_data (
	created_date varchar(255) NULL,
	customer_id varchar(255) NULL,
	email varchar(255) NULL,
	id varchar(255) NOT NULL,
	nit varchar(255) NULL,
	social_reazon varchar(255) NULL,
	CONSTRAINT biller_data_pkey PRIMARY KEY (id)
);

CREATE TABLE public.contract (
	amount float4 NOT NULL,
	quotas int4 NOT NULL,
	catering_plan_id varchar(255) NULL,
	created_date varchar(255) NULL,
	customer_id varchar(255) NULL,
	description varchar(255) NULL,
	id varchar(255) NOT NULL,
	status varchar(255) NULL,
	CONSTRAINT contract_pkey PRIMARY KEY (id)
);

CREATE TABLE public.customer (
	address varchar(255) NULL,
	birt_date varchar(255) NULL,
	created_date varchar(255) NULL,
	id varchar(255) NOT NULL,
	"name" varchar(255) NULL,
	phone varchar(255) NULL,
	status varchar(255) NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id)
);

CREATE TABLE public.invoice_detail (
	amount float4 NOT NULL,
	discount float4 NOT NULL,
	subtotal float4 NOT NULL,
	unit_price float4 NOT NULL,
	code varchar(255) NULL,
	concepts varchar(255) NULL,
	created_date varchar(255) NULL,
	currency varchar(255) NULL,
	description varchar(255) NULL,
	id varchar(255) NOT NULL,
	invoice_id varchar(255) NULL,
	CONSTRAINT invoice_detail_pkey PRIMARY KEY (id)
);

CREATE TABLE public.invoices (
	tax_credit_amount float4 NOT NULL,
	total float4 NOT NULL,
	total_discounts float4 NOT NULL,
	aaauthorization varchar(255) NULL,
	company_address varchar(255) NULL,
	company_branch varchar(255) NULL,
	company_city varchar(255) NULL,
	company_country varchar(255) NULL,
	company_name varchar(255) NULL,
	company_nit varchar(255) NULL,
	company_phone varchar(255) NULL,
	contract_id varchar(255) NULL,
	created_date varchar(255) NULL,
	currency varchar(255) NULL,
	customer_address varchar(255) NULL,
	customer_email varchar(255) NULL,
	customer_nit varchar(255) NULL,
	customer_social_reason varchar(255) NULL,
	id varchar(255) NOT NULL,
	issue_date varchar(255) NULL,
	legend1 varchar(255) NULL,
	legend2 varchar(255) NULL,
	legend3 varchar(255) NULL,
	literal_amount varchar(255) NULL,
	"number" varchar(255) NULL,
	payment_id varchar(255) NULL,
	"period" varchar(255) NULL,
	place_of_emission varchar(255) NULL,
	status varchar(255) NULL,
	CONSTRAINT invoices_pkey PRIMARY KEY (id)
);

CREATE TABLE public.ordenes (
	amount float4 NOT NULL,
	contract_id varchar(255) NULL,
	created_date varchar(255) NULL,
	description varchar(255) NULL,
	id varchar(255) NOT NULL,
	status varchar(255) NULL,
	CONSTRAINT ordenes_pkey PRIMARY KEY (id)
);

CREATE TABLE public.payment_method (
	created_date varchar(255) NULL,
	descripcion varchar(255) NULL,
	id varchar(255) NOT NULL,
	"label" varchar(255) NULL,
	"name" varchar(255) NULL,
	status varchar(255) NULL,
	"type" varchar(255) NULL,
	CONSTRAINT payment_method_pkey PRIMARY KEY (id)
);

CREATE TABLE public.payment (
	amount float4 NOT NULL,
	created_date varchar(255) NULL,
	currency varchar(255) NULL,
	id varchar(255) NOT NULL,
	order_id varchar(255) NULL,
	payment_method_id varchar(255) NULL,
	status varchar(255) NULL,
	CONSTRAINT payment_pkey PRIMARY KEY (id),
	CONSTRAINT fk_payment_method FOREIGN KEY (payment_method_id) REFERENCES public.payment_method(id)
);