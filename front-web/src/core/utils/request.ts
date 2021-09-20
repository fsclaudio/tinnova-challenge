import axios,{Method} from 'axios';

type RequestParams = {
    method?: Method;
    url: string;
    data?: object | string;
    params?: object;
    headers?: object;
}



const BASE_URL ='http://localhost:8080';

axios.interceptors.response.use(function (response){
  return response;
}, function(error){
  console.log(Response)
  if (error.response.status === 401){
    //colocar um toaster
  }
  return Promise.reject(error)
});

export const makeRequest =({method = 'GET', url, data, params, headers}: RequestParams) => {
   return axios ({
     method,
     url: `${BASE_URL}${url}`,
     data,
     params, 
     headers,
   });
}


