CREATE TABLE public.productos
(
    codigo serial,
    tipo_producto text,
    nombre text,
    existencias numeric(5, 0),
    preciocompra numeric(10, 0),
    precioventa numeric(10, 0),
    proveedor text,
    fechavencimiento date,
    PRIMARY KEY (codigo)
);

ALTER TABLE IF EXISTS public.productos
    OWNER to usercentral;