import axios from "axios";

const REST_API_BASE_URL = "http://localhost:8080/api/vacations";

export const createVacation = (vacation) => axios.post(REST_API_BASE_URL, vacation);
export const getVacation = (vacationId) => axios.get(REST_API_BASE_URL + '/' + vacationId);
export const updateVacation = (vacationId, vacation) => axios.put(REST_API_BASE_URL + '/' + vacationId, vacation);
export const deleteVacation = (vacationId) => axios.delete(REST_API_BASE_URL + '/' + vacationId);