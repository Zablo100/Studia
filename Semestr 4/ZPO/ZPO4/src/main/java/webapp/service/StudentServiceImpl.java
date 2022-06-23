package webapp.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import webapp.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    private static final Logger logger = LoggerFactory.getLogger(ProjektServiceImpl.class);
    private static final String RESOURCE_PATH = "/api/studenci";

    @Value("${rest.server.url}")

    private String serverUrl;
    private RestTemplate restTemplate;

    @Autowired
    public StudentServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Student> getStudent(Integer studentId) {
        URI url = ServiceUtil.getUriComponent(serverUrl, "api/student/" + studentId.toString())
                .build()
                .toUri();
        return Optional.ofNullable(restTemplate.getForObject(url, Student.class));
    }

    @Override
    public Student setStudent(Student student) {
        if (student.getstudent_id() != null) { // modyfikacja istniejącego projektu
            String url = getUriStringComponent(student.getstudent_id());
            logger.info("REQUEST -> PUT {}", url);
            restTemplate.put(url, student);
            return student;
        } else {//utworzenie nowego projektu
            // po dodaniu projektu zwracany jest w nagłówku Location - link do utworzonego zasobu
            HttpEntity<Student> request = new HttpEntity<>(student);
            String url = getUriStringComponent();
            logger.info("REQUEST -> POST {}", url);
            URI location = restTemplate.postForLocation(url, request);
            logger.info("REQUEST (location) -> GET {}", location);
            return restTemplate.getForObject(location, Student.class);
        }
    }

    @Override
    public void deleteStudent(Integer studentId) {
        URI url = ServiceUtil.getUriComponent(serverUrl, getResourcePath(studentId))
                .build()
                .toUri();
        logger.info("REQUEST -> DELETE {}", url);
        restTemplate.delete(url);
    }

    @Override
    public Page<Student> getStudent(Pageable pageable) {
        URI uri = ServiceUtil.getURI(serverUrl, RESOURCE_PATH, pageable);
        return ServiceUtil.getPage(uri, restTemplate,
                new ParameterizedTypeReference<RestResponsePage<Student>>() {});
    }

    @Override
    public Page<Student> searchByImie(String imie, Pageable pageable) {
        return null;
    }

    private String getResourcePath() {
        return RESOURCE_PATH;
    }

    private String getResourcePath(Integer id) {
        return RESOURCE_PATH + "/" + id;
    }

    private String getUriStringComponent() {
        return serverUrl + getResourcePath();
    }

    private String getUriStringComponent(Integer id) {
        return serverUrl + getResourcePath(id);
    }
}
