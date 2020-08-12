import http from "../http-common";

class UploadFilesService {
  upload(file, onUploadProgress) {
    let formData = new FormData();

    formData.append("file", file);

    return http.post("/upload", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
      onUploadProgress,
    });
  }

  getFiles(filename) {
    return http.get("/files/" + filename);
  }

  getFilesToShow(filename) {
    return http.get("/files/fileInfo/" + filename);
  }
}

export default new UploadFilesService();