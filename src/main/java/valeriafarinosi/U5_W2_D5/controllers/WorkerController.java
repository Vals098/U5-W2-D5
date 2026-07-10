package valeriafarinosi.U5_W2_D5.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valeriafarinosi.U5_W2_D5.entities.Worker;
import valeriafarinosi.U5_W2_D5.exceptions.ValidationException;
import valeriafarinosi.U5_W2_D5.payloads.NewWorkerDTO;
import valeriafarinosi.U5_W2_D5.payloads.WorkerResponseDTO;
import valeriafarinosi.U5_W2_D5.services.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    //    WorkerService's DI
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    //    POST http://localhost:3003/workers + payload -> newWorker
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //201
    public WorkerResponseDTO createWorker(@RequestBody @Validated NewWorkerDTO payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errorsList);
        }

        Worker saved = this.workerService.saveWorker(payload);
        return new WorkerResponseDTO(saved.getWorkerId());

    }

    //    GET http://localhost:3003/workers -> List<Worker>
    @GetMapping
    public List<Worker> getAllWorkers() {
        return this.workerService.getAllWorkers();
    }

    //    GET http://localhost:3003/workers/{workerId} -> Worker
    @GetMapping("/{workerId}")
    public Worker findById(@PathVariable int workerId) {
        return this.workerService.findById(workerId);
    }

    //    PUT http://localhost:3003/workers/{workerId} + payload -> updated Worker with workerId
    @PutMapping("/{workerId}")
    public Worker findByIdAndUpdate(@PathVariable int workerId, @RequestBody @Validated NewWorkerDTO payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errorsList);
        }

        return this.workerService.findByIdAndUpdate(workerId, payload);

    }

    //    DELETE http://localhost:3003/workers/{workerId} -> delete Worker with workerId
    @DeleteMapping("/{workerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int workerId) {
        this.workerService.findByIdAndDelete(workerId);
    }


}
