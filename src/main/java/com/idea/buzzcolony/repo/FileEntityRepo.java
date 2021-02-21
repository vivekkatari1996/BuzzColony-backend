package com.idea.buzzcolony.repo;

import com.idea.buzzcolony.enums.base.FileType;
import com.idea.buzzcolony.model.base.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 20/02/21
 */
@Repository
public interface FileEntityRepo extends JpaRepository<FileEntity, Long> {
    Optional<FileEntity> findByRefIdAndFileType(Long id, FileType post);

    @Query(nativeQuery = true, value = "delete from file_entity where uuid=?1")
    @Modifying
    @Transactional
    void deleteByIdManual(String uuid);

    List<FileEntity> findByRefIdInAndFileType(List<Long> ids, FileType post);
}
