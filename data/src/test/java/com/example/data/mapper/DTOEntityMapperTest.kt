package com.example.data.mapper

import com.example.data.utils.MockData
import org.junit.Assert.assertEquals
import org.junit.Test

class DTOEntityMapperTest {



    @Test
    fun `mapToDomain should correctly map nested UserDTO to Domain Entity`() {
        // Arrange
        val dto = MockData.mockCommentDTO
        val expectedDomain = MockData.mockDomainComment

        // Act
        val result = dto.toCommentEntity()

        // Assert
        assertEquals(expectedDomain.id, result.id)
        assertEquals(expectedDomain.body, result.body)
        
        // CRITICAL: Check that the nested username was mapped correctly
        assertEquals(expectedDomain.user.username, result.user.username)
    }
}