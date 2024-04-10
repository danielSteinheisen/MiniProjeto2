package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.dto.DisciplinaFiltro;
import br.com.fullstackeducation.miniprojeto2.entity.DisciplinaEntity;
import br.com.fullstackeducation.miniprojeto2.entity.ProfessorEntity;
import br.com.fullstackeducation.miniprojeto2.exception.NotFoundException;
import br.com.fullstackeducation.miniprojeto2.repository.DisciplinaRepository;
import br.com.fullstackeducation.miniprojeto2.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DisciplinaServiceImpl implements DisciplinaService {


    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorService professorService;

    public DisciplinaServiceImpl(DisciplinaRepository disciplinaRepository, ProfessorService professorService) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorService = professorService;
    }

    public DisciplinaEntity criarDisciplina(DisciplinaEntity disciplinaNova) {
        disciplinaNova.setId(null);

        log.info("Criando disciplina -> Salvar: \n{}\n", JsonUtil.objetoParaJson(disciplinaNova));
        ProfessorEntity professor = professorService.buscarProfessorPorId(disciplinaNova.getProfessor_id().getId());
        disciplinaNova.setProfessor_id(professor);

        log.info("Criando disciplina -> Salvo com sucesso");
        log.debug("Criando disciplina -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(disciplinaNova));
        return disciplinaRepository.save(disciplinaNova);
    }

    @Override
    public List<DisciplinaEntity> listarDisciplinas(DisciplinaFiltro filtro) {
        List<DisciplinaEntity> disciplinas;

        if (StringUtils.hasText(filtro.getNome())) {
            log.info("Buscando todas as disciplinas -> com nome ({})", filtro.getNome());
            disciplinas = disciplinaRepository.findByNomeContainingIgnoreCase(filtro.getNome());
        } else {
            log.info("Buscando todas as disciplinas");
            disciplinas = disciplinaRepository.findAll();
        }

        log.info("Buscando todas as disciplinas -> {} disciplinas encontradas", disciplinas.size());
        log.debug("Buscando todas as disciplinas -> Registros encontrados:\n{}\n",
                JsonUtil.objetoParaJson(disciplinas));
        return disciplinas;
    }

    public DisciplinaEntity buscarDisciplinaPorId(Long id) {
        log.info("Buscando disciplina por ID: {}", id);
        Optional<DisciplinaEntity> disciplina = disciplinaRepository.findById(id);
        if (disciplina.isEmpty()) {
            log.error("Buscando disciplina por id {} -> NÃO Encontrado", id);
            return disciplinaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada com o ID: " + id));
        }
        log.info("Buscando disciplina por id ({}) -> Encontrado", id);
        log.debug("Buscando disciplina por id ({}) -> Registro encontrado:\n{}\n", id, JsonUtil.objetoParaJson(disciplina.get()));
        return disciplina.get();
    }

    public DisciplinaEntity atualizarDisciplina(Long id, DisciplinaEntity disciplina) {
        if (!disciplinaRepository.existsById(id)) {
            log.info("Alterando disciplina com id ({}) -> Salvar: \n{}\n", id, JsonUtil.objetoParaJson(disciplina));
            throw new NotFoundException("Disciplina não encontrada com o ID: " + id);
        }
        disciplina.setId(id);
        log.info("Alterando disciplina -> Salvo com sucesso");
        log.debug("Alterando disciplina -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(disciplina));
        return disciplinaRepository.save(disciplina);
    }

    public void excluirDisciplina(Long id) {
        if (!disciplinaRepository.existsById(id)) {
            log.info("Excluindo disciplina com id ({}) -> Excluindo", id);
            throw new NotFoundException("Disciplina não encontrada com o ID: " + id);
        }
        disciplinaRepository.deleteById(id);
        log.info("Excluindo disciplina com id ({}) -> Excluído com sucesso", id);
    }
}