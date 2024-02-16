import http from "../http-common";

class AgendaDataService {
  getAll() {
    return http.get("/agenda");
  }

  get(id) {
    return http.get(`/agenda/${id}`);
  }

  create(data) {
    return http.post("/agenda", data);
  }

  update(id, data) {
    return http.put(`/agenda/${id}`, data);
  }

  delete(id) {
    return http.delete(`/agenda/${id}`);
  }

  deleteAll() {
    return http.delete(`/agenda`);
  }

/*  findByTitle(titulo) {
    return http.get(`/agenda?title=${titulo}`);
  }*/
}

export default new AgendaDataService();