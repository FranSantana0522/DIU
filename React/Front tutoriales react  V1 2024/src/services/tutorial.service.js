import http from "../http-common";

class TutorialDataService {
  getAll() {
    return http.get("/tutorials");
  }

  get(id) {
    return http.get(`/tutorial/${id}`);
  }

  create(data) {
    return http.post("/tutorials", data);
  }

  update(id, data) {
    return http.put(`/editTuto/${id}`, data);
  }

  delete(id) {
    return http.delete(`/deleteTuto/${id}`);
  }

  deleteAll() {
    return http.delete(`/deleteAll`);
  }

  findByTitle(titulo) {
    return http.get(`/tutorials?title=${titulo}`);
  }
 //findByTitle(title) {
 //    return http.get(`/tutorials/title/${title}`);
 //  }
}

export default new TutorialDataService();