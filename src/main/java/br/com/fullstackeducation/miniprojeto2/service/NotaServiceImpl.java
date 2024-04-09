package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.entity.DisciplinaEntity;
import br.com.fullstackeducation.miniprojeto2.entity.MatriculaEntity;
import br.com.fullstackeducation.miniprojeto2.entity.NotaEntity;
import br.com.fullstackeducation.miniprojeto2.entity.ProfessorEntity;
import br.com.fullstackeducation.miniprojeto2.exception.error.NotFoundException;
import br.com.fullstackeducation.miniprojeto2.repository.MatriculaRepository;
import br.com.fullstackeducation.miniprojeto2.repository.NotaRepository;
import br.com.fullstackeducation.miniprojeto2.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotaServiceImpl implements NotaService {

    private final NotaRepository notaRepository;
    private final MatriculaRepository matriculaRepository;

    /*
    @Override
    public NotaEntity criarNota(Long matriculaId, NotaEntity notaNova, BigDecimal coeficiente) {
        notaNova.setId(null);
        MatriculaEntity matricula = matriculaRepository.findById(matriculaId).orElseThrow(() -> new NotFoundException("Matrícula " + matriculaId +" não encontrada!"));
        DisciplinaEntity disciplina = matricula.getDisciplina();
        if (disciplina == null) {
            throw new NotFoundException("Não foi possível encontrar uma disciplina nesta matrícula!");
        }
        ProfessorEntity professor = disciplina.getProfessorId();
        if (professor == null) {
            throw new NotFoundException("Não foi possível encontrar um professor!");
        }
        log.info("Criando nota -> Salvar: \n{}\n", JsonUtil.objetoParaJson(notaNova));
        notaNova.setProfessor(professor);
        notaNova.setCoeficiente(coeficiente);
        NotaEntity nota = notaRepository.save(notaNova);
        atualizarMediaFinal(matricula);
        log.info("Criando nota -> Salvo com sucesso");
        log.debug("Criando nota -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(notaNova));
        return notaNova;
    }*/

    @Override
    public NotaEntity criarNota(NotaEntity notaNova) {
        notaNova.setId(null);
        log.info("Criando nota -> Salvar: \n{}\n", JsonUtil.objetoParaJson(notaNova));
        NotaEntity nota = notaRepository.save(notaNova);
        log.info("Criando nota -> Salvo com sucesso");
        log.debug("Criando nota -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(notaNova));
        return nota;
    }

    @Override
    public List<NotaEntity> listarNotas() {
        log.info("Buscando todas as notas");
        List<NotaEntity> notas = notaRepository.findAll();
        log.info("Buscando todas as notas -> {} notas encontradas", notaRepository.findAll().size());
        log.debug("Buscando todas as notas -> Registros encontrados:\n{}\n", JsonUtil.objetoParaJson(notaRepository));
        return notas;
    }

    @Override
    public NotaEntity buscarNotaPorId(Long id) {
        log.info("Buscando nota por ID: {}", id);
        Optional<NotaEntity> nota = notaRepository.findById(id);
        if (nota.isEmpty()) {
            log.error("Buscando nota por id {} -> NÃO Encontrado", id);
            return notaRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Nota não encontrada com o ID: " + id));
        }
        log.info("Buscando nota por id ({}) -> Encontrado", id);
        log.debug("Buscando nota por id ({}) -> Registro encontrado:\n{}\n", id, JsonUtil.objetoParaJson(nota.get()));
        return nota.get();
    }

    @Override
    public NotaEntity atualizarNota(Long id, NotaEntity nota) {
        if (!notaRepository.existsById(id)) {
            log.info("Alterando nota com id ({}) -> Salvar: \n{}\n", id, JsonUtil.objetoParaJson(nota));
            throw new NotFoundException("Nota não encontrada com o ID: " + id);
        }
        nota.setId(id);
        log.info("Alterando nota -> Salvo com sucesso");
        log.debug("Alterando nota -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(nota));
        return notaRepository.save(nota);
    }

    @Override
    public NotaEntity calcularMedia(Long matriculaId) {
        return null;
    }

    @Override
    public void excluirNota(Long id) {
        if (!notaRepository.existsById(id)) {
            log.info("Excluindo nota com id ({}) -> Excluindo", id);
            throw new NotFoundException("Nota não encontrada com o ID: " + id);
        }
        notaRepository.deleteById(id);
        log.info("Excluindo nota com id ({}) -> Excluído com sucesso", id);

    }

    /*
    @Override
    public void excluirNota(Long id) {
        if (!notaRepository.existsById(id)) {
            log.info("Excluindo nota com id ({}) -> Excluindo", id);
            throw new NotFoundException("Nota não encontrada com o ID: " + id);
        }

        MatriculaEntity matricula = notaRepository.getMatricula();
        if (matricula == null) {
            throw new NotFoundException("Nota não encontrada nesta matrícula!");
        }
        notaRepository.deleteById(id);
        atualizarMediaFinal(matricula);
        log.info("Excluindo nota com id ({}) -> Excluído com sucesso", id);
    }*/

    /*
    @Override
    public List<NotaEntity> listarNotasPorMatricula(Long matriculaId) {
        MatriculaEntity matricula = matriculaRepository.findById(matriculaId).orElseThrow(() -> new NotFoundException("Matrícula " + + matriculaId + " não encontrada!"));
        log.info("Buscando todas as notas");
        List<NotaEntity> notas = matricula.getNotas();
        log.info("Buscando todas as notas -> {} notas encontradas", notaRepository.findAll().size());
        log.debug("Buscando todas as notas -> Registros encontrados:\n{}\n", JsonUtil.objetoParaJson(notaRepository));
        return matricula.getNotas();
    }*/
}
