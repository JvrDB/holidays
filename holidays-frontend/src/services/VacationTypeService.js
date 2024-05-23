import axios from "axios";

const REST_API_BASE_URL = "http://localhost:8080/api/vacation-types";

export const listVacationTypes = () => axios.get(REST_API_BASE_URL);
export const createVacationType = (vacationType) => axios.post(REST_API_BASE_URL, vacationType);
export const getVacationType = (vacationTypeId) => axios.get(REST_API_BASE_URL + '/' + vacationTypeId);
export const updateVacationType = (vacationTypeId, vacationType) => axios.put(REST_API_BASE_URL + '/' + vacationTypeId, vacationType);
export const deleteVacationType = (vacationTypeId) => axios.delete(REST_API_BASE_URL + '/' + vacationTypeId);