package valeriafarinosi.U5_W2_D5.services;

import com.cloudinary.Cloudinary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import valeriafarinosi.U5_W2_D5.entities.Worker;
import valeriafarinosi.U5_W2_D5.exceptions.BadRequestException;
import valeriafarinosi.U5_W2_D5.exceptions.NotFoundException;
import valeriafarinosi.U5_W2_D5.payloads.NewWorkerDTO;
import valeriafarinosi.U5_W2_D5.repositories.WorkerRepository;

import java.util.List;

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


}
