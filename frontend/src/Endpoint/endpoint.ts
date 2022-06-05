export const BASE_URL = "http://localhost.8082";

export const LOGIN_URL = `${BASE_URL}/api/auth/authenticate`;
export const UPLOAD_MULTIPLE_URL = `${BASE_URL}/uploadMultipleFiles`;
export const UPLOAD_SIGNLE_URL = `${BASE_URL}/uploadFile`;
export const CREATE_CATEGORY_URL = `${BASE_URL}/admin/category/create`;
export const CREATE_PRODUCT_URL = `${BASE_URL}/admin/product/create`;

export const FIND_ALL_CATEGORY_URL = `${BASE_URL}/category`;
export const FIND_ALL_PRODUCT_URL = `${BASE_URL}/product`;
export const FIND_PRODUCT_BY_NAMESEO_URL = `${BASE_URL}/product/findByNameSeo/`;
export const FIND_CATEGORY_BY_IS_ACTIVE_URL = `${BASE_URL}/category/findByIsActive`;
export const FIND_PRODUCTS_BY_CATEGORY_NAMESEO = `${BASE_URL}/product/findByCategory/`;
export const DELETE_PRODUCT_BY_ID_URL = `${BASE_URL}/admin/product/delete/`;
export const DELETE_CATEGORY_BY_ID_URL = `${BASE_URL}/admin/category/delete/`;
export const UPDATE_PRODUCT = `${BASE_URL}/admin/product/edit/`;
export const FIND_CATEGORY_BY_ID = `${BASE_URL}/category/findById/`;
export const UPDATE_CATEGORY = `${BASE_URL}/admin/category/edit/`;
export const FIND_ALL_PRODUCT_BY_CONTAIN_NAME_URL = `${BASE_URL}/product/findByContainName`;

export const switchCategoryById = (id:number) => {
  return `${BASE_URL}/admin/category/switch/${id}`;
};

export const findProductByState = (state = 0) => {
  return `${BASE_URL}/product/findByState/${state}`;
};
