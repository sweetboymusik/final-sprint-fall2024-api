package com.keyin.gate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GateService {
    private GateRepository gateRepository;

    @Autowired
    public GateService(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    public Gate addGate(Gate gate) {
        return gateRepository.save(gate);
    }

    public void deleteGate(int id) {
        gateRepository.deleteById(id);
    }
}