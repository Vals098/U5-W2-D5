package valeriafarinosi.U5_W2_D5.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import valeriafarinosi.U5_W2_D5.entities.Worker;
import valeriafarinosi.U5_W2_D5.exceptions.BadRequestException;
import valeriafarinosi.U5_W2_D5.exceptions.NotFoundException;
import valeriafarinosi.U5_W2_D5.payloads.NewWorkerDTO;
import valeriafarinosi.U5_W2_D5.repositories.WorkerRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WorkerService {

    //    WorkerRepository's DI
    private final WorkerRepository workerRepository;

    //    Cloudinary's DI
    private final Cloudinary fileUploader;

    public WorkerService(WorkerRepository workerRepository, Cloudinary fileUploader) {
        this.workerRepository = workerRepository;
        this.fileUploader = fileUploader;
    }

    //  --------------------------  WORKERS CRUD -----------------------------
//    SAVE
    public Worker saveWorker(NewWorkerDTO payload) {
//        controls
        if (this.workerRepository.existsByEmail(payload.email()))
            throw new BadRequestException("The email address " + payload.email() + " is already in use.");

        if (this.workerRepository.existsByUsername(payload.username()))
            throw new BadRequestException("The username " + payload.username() + " is already in taken.");

//        create new worker
        Worker newWorker = new Worker(payload.username(), payload.name(), payload.surname(), payload.email());

//        save new worker and log message
        Worker saved = this.workerRepository.save(newWorker);
        log.info("The worker " + newWorker + " has been saved!");
        return saved;

    }

    //    FINDALL
    public List<Worker> getAllWorkers() {
        return this.workerRepository.findAll();
    }

    //    FINDBYID
    public Worker findById(int workerId) {
        return this.workerRepository.findById(workerId).orElseThrow(() -> new NotFoundException(workerId));
    }

    //   FINDBYID AND UPDATE
    public Worker findByIdAndUpdate(int workerId, NewWorkerDTO payload) {

//        controls
        Worker found = this.workerRepository.findById(workerId).orElseThrow(() -> new NotFoundException(workerId));

//        set payload data
        found.setUsername(payload.username());
        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setEmail(payload.email());

//        save(update) worker
        Worker updated = this.workerRepository.save(found);

        return updated;

    }

    //    DELETE
    public void findByIdAndDelete(int workerId) {

//        controls
        Worker found = this.workerRepository.findById(workerId).orElseThrow(() -> new NotFoundException(workerId));

//         delete
        this.workerRepository.delete(found);

    }

    //   UPDATE WORKER PROFILE PIC (AVATAR)
    public Worker updateAvatar(int workerId, MultipartFile file) {
//  1. eventuali controlli tipo file/formato
        if (file.isEmpty()) {
            throw new BadRequestException("Please select an image.");
        }
//  2. findByIdWorker
        Worker worker = findById(workerId);
//  3. upload del file du Cloudinary
        try {
            Map result = fileUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("secure_url");
            System.out.println(url);
//  4. se tutto va bene, Cloudinary restituirà l'url dell'img
            worker.setAvatarUrl(url);

            return workerRepository.save(worker);

        } catch (IOException e) {
            throw new BadRequestException("Error while loading the image.");
        }

    }


}
