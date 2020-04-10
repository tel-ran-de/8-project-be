package de.telran.repositiory;

import de.telran.entity.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingRepositiory extends JpaRepository<Tracking,Long> {
}
